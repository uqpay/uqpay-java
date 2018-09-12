package com.uqpay.sdk;

import com.uqpay.sdk.dto.PayOptions;
import com.uqpay.sdk.dto.PaygateParams;
import com.uqpay.sdk.dto.common.AuthDTO;
import com.uqpay.sdk.dto.common.BankCardCompatibleDTO;
import com.uqpay.sdk.dto.common.BankCardDTO;
import com.uqpay.sdk.dto.common.CreditCardDTO;
import com.uqpay.sdk.dto.enroll.EnrollOrder;
import com.uqpay.sdk.dto.enroll.VerifyOrder;
import com.uqpay.sdk.dto.pay.PayOrder;
import com.uqpay.sdk.dto.preAuth.PreAuthOrder;
import com.uqpay.sdk.exception.UqpayPayFailException;
import okhttp3.Request;
import okhttp3.Response;
import com.uqpay.sdk.config.MerchantConfig;
import com.uqpay.sdk.config.PaygateConfig;
import com.uqpay.sdk.dto.operation.OrderCancel;
import com.uqpay.sdk.dto.operation.OrderQuery;
import com.uqpay.sdk.dto.operation.OrderRefund;
import com.uqpay.sdk.dto.result.*;
import com.uqpay.sdk.exception.UqpayRSAException;
import com.uqpay.sdk.exception.UqpayResultVerifyException;
import com.uqpay.sdk.utils.*;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;

public class UqpayPaygate {

  private PaygateConfig paygateConfig;

  private MerchantConfig merchantConfig;

  private AuthDTO auth;

  private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

  private <T> void validateRequestParams(T object, String msg) {
    Set<ConstraintViolation<T>> violations = validator.validate(object);
    if (violations.size() > 0) {
      throw new ConstraintViolationException(msg, violations);
    }
  }

  private void validatePayData(PaygateParams... params) {
    Arrays.asList(params).forEach(paygateParams -> validateRequestParams(paygateParams, "pay data invalid for uqpay payment"));
  }

  UqpayPaygate(Builder builder) {
    this.paygateConfig = builder.paygateConfig;
    this.merchantConfig = builder.merchantConfig;
    this.auth = new AuthDTO();
    this.auth.setAgentId(merchantConfig.getAgentId());
    this.auth.setMerchantId(merchantConfig.getId());
  }

  public PaygateConfig paygateConfig() {
    return this.paygateConfig;
  }

  public MerchantConfig merchantConfig() {
    return this.merchantConfig;
  }

  public String apiUrl(String url) {
    return paygateConfig.getApiRoot() + url;
  }

  private <T> T directPost(Map<String, String> paramsMap, String url, Class<T> resultClass) throws IOException, UqpayRSAException, UqpayResultVerifyException, UqpayPayFailException {
    Request request = PayUtil.generateRequest(PayUtil.signParams(paramsMap, paygateConfig), url);
    Response response = PayUtil.doSyncFormRequest(request);
    Map<String, String> resultMap = Tools.json2map(response.body().string());
    T result = PayUtil.map2Params(resultClass, resultMap);
    PayUtil.verifyUqpayNotice(resultMap, paygateConfig);
    return result;
  }

  private String redirectPost(Map<String, String> paramsMap, String url) throws IOException, UqpayRSAException, UqpayPayFailException {
    Request request = PayUtil.generateRequest(PayUtil.signParams(paramsMap, paygateConfig), url);
    Response response = PayUtil.doSyncFormRequest(request);
    return response.body().string();
  }

  private <T> T payDirectRequest(Map<String, String> paramsMap, Class<T> resultClass) throws IOException, UqpayRSAException, UqpayResultVerifyException, UqpayPayFailException {
    return directPost(paramsMap, apiUrl(Constants.PAYGATE_API_PAY), resultClass);
  }

  private final QRResult QRCodePayment(PaygateParams pay, String url) throws IOException, UqpayRSAException, UqpayResultVerifyException, UqpayPayFailException {
    PayOptions payOptions = (PayOptions) pay;
    if (payOptions.getScanType() == null) throw new NullPointerException("uqpay qr code payment need Scan Type");
    if (payOptions.getScanType().equals(UqpayScanType.Merchant) && payOptions.getIdentity() == null)
      throw new NullPointerException("uqpay qr code payment need the identity data when scan type is merchant");
    Map<String, String> paramsMap = PayUtil.params2Map(pay, this.auth);
    return directPost(paramsMap, url, QRResult.class);
  }

  private final String RedirectPayment(PaygateParams pay, String url) throws IOException, UqpayRSAException, UqpayPayFailException {
    PayOptions payOptions = (PayOptions) pay;
    if (payOptions.getReturnUrl() == null || payOptions.getReturnUrl().equals(""))
      throw new NullPointerException("uqpay online payment need sync notice url");
    Map<String, String> paramsMap = PayUtil.params2Map(pay, this.auth);
    return redirectPost(paramsMap, url);
  }

  private final CardResult CreditCardPayment(PaygateParams pay, BankCardDTO bankCard, String url) throws IOException, UqpayRSAException, UqpayResultVerifyException, UqpayPayFailException {
    Map<String, String> paramsMap = PayUtil.params2Map(pay, bankCard, this.auth);
    return directPost(paramsMap, url, CardResult.class);
  }

  private final CardResult MerchantHostPayment(PaygateParams pay, BankCardCompatibleDTO bankCard, String url) throws UqpayRSAException, UqpayResultVerifyException, UqpayPayFailException, IOException {
    if (bankCard.getCardType().equals(BankCardType.Credit)) {
      if (bankCard.getExpireMonth() == null || bankCard.getExpireMonth().equals("") || bankCard.getExpireYear() == null || bankCard.getExpireYear().equals("")) {
        throw new NullPointerException("uqpay merchant host payment if the card type is credit, the expire date info is required");
      }
    }
    Map<String, String> paramsMap = PayUtil.params2Map(pay, bankCard, this.auth);
    return directPost(paramsMap, url, CardResult.class);
  }

  private final String ThreeDSecurePayment(PaygateParams pay, BankCardDTO bankCard, String url) throws IOException, UqpayRSAException, UqpayPayFailException {
    PayOptions payOptions = (PayOptions) pay;
    if (payOptions.getReturnUrl() == null || payOptions.getReturnUrl().equals(""))
      throw new NullPointerException("uqpay 3D secure payment need sync notice url");
    Map<String, String> paramsMap = PayUtil.params2Map(pay, bankCard, this.auth);
    return redirectPost(paramsMap, url);
  }

  private final InAppResult InAppPayment(PaygateParams pay, String url) throws IOException, UqpayRSAException, UqpayResultVerifyException, UqpayPayFailException {
    if (((PayOptions) pay).getClient().equals(PaymentSupportClient.PC_WEB))
      throw new NullPointerException("uqpay in-app payment not support pc client");
    Map<String, String> paramsMap = PayUtil.params2Map(pay, this.auth);
    return directPost(paramsMap, url, InAppResult.class);
  }

  private final TransResult PreAuthFinish(PreAuthOrder order) throws UqpayRSAException, UqpayResultVerifyException, UqpayPayFailException, IOException {
    if (order.getUqOrderId() <= 0) {
      throw new NullPointerException("uqpay order id is required");
    }
    Map<String, String> paramsMap = PayUtil.params2Map(order, this.auth);
    return directPost(paramsMap, apiUrl(Constants.PAYGATE_API_PRE_AUTH), TransResult.class);
  }

  private final CardResult EnrollCard(EnrollOrder order) throws UqpayRSAException, UqpayResultVerifyException, UqpayPayFailException, IOException {
    Map<String, String> paramsMap = PayUtil.params2Map(order, this.auth);
    return directPost(paramsMap, apiUrl(Constants.PAYGATE_API_ENROLL), CardResult.class);
  }

  private final CardResult VerifyPhone(VerifyOrder order) throws UqpayRSAException, UqpayResultVerifyException, UqpayPayFailException, IOException {
    Map<String, String> paramsMap = PayUtil.params2Map(order, this.auth);
    return directPost(paramsMap, apiUrl(Constants.PAYGATE_API_VERIFY), CardResult.class);
  }

  //===========================================
  // Pay API
  //===========================================

  /**
   * this pay api support those pay methods which no need input other params(get from the consumers, eg. credit card info)
   *
   * @param order
   * @return
   * @throws UqpayRSAException
   * @throws UqpayResultVerifyException
   * @throws UqpayPayFailException
   * @throws IOException
   */
  public final Object pay(PayOrder order) throws UqpayRSAException, UqpayResultVerifyException, UqpayPayFailException, IOException {
    order.setTradeType(UqpayTradeType.pay);
    validatePayData(order);
    PayMethodEnum scenes = PayMethodEnum.valueOf(order.getMethodId());
    switch (scenes.getScenes()) {
      case QRCode:
        return this.QRCodePayment(order, apiUrl(Constants.PAYGATE_API_PAY));
      case RedirectPay:
        return this.RedirectPayment(order, apiUrl(Constants.PAYGATE_API_PAY));
      case InApp:
        return this.InAppPayment(order, apiUrl(Constants.PAYGATE_API_PAY));
      default:
        return null;
    }
  }

  /**
   * this pay api for moment just support the bank card payment
   * for the merchant host , the bank card required params see the {@link BankCardCompatibleDTO}
   *
   * @param order required
   * @param bankCard required
   * @return
   * @throws UqpayRSAException
   * @throws UqpayResultVerifyException
   * @throws UqpayPayFailException
   * @throws IOException
   */
  public final Object pay(PayOrder order, BankCardDTO bankCard) throws UqpayRSAException, UqpayResultVerifyException, UqpayPayFailException, IOException {
    order.setTradeType(UqpayTradeType.pay);
    validatePayData(order);
    PayMethodEnum scenes = PayMethodEnum.valueOf(order.getMethodId());
    switch (scenes.getScenes()) {
      case CreditCard:
        switch (order.getMethodId()) {
          case PayMethod.AMEX:
          case PayMethod.JCB:
          case PayMethod.Master:
          case PayMethod.VISA:
            validatePayData(bankCard);
            break;
          default:
            validatePayData(CreditCardDTO.valueOf(bankCard));
        }
        return this.CreditCardPayment(order, bankCard, apiUrl(Constants.PAYGATE_API_PAY));
      case ThreeDCreditCard:
        validatePayData(bankCard);
        return this.ThreeDSecurePayment(order, bankCard, apiUrl(Constants.PAYGATE_API_PAY));
      case MerchantHost:
        return this.MerchantHostPayment(order, BankCardCompatibleDTO.valueOf(bankCard), apiUrl(Constants.PAYGATE_API_PAY));
      default:
        return null;
    }
  }

  //===========================================
  // PreAuth API
  //===========================================

  /**
   * check the code ,it's easy to understand.
   * be sure set the right trade type
   * @param order required
   * @param bankCard optional
   * @return
   * @throws UqpayRSAException
   * @throws UqpayResultVerifyException
   * @throws UqpayPayFailException
   * @throws IOException
   */
  public final Object preAuth(PreAuthOrder order, BankCardDTO bankCard) throws UqpayRSAException, UqpayResultVerifyException, UqpayPayFailException, IOException {
    validatePayData(order);
    switch (order.getTradeType()) {
      case preauth:
        PayMethodEnum scenes = PayMethodEnum.valueOf(order.getMethodId());
        switch (scenes.getScenes()) {
          case InApp:
            return this.InAppPayment(order, apiUrl(Constants.PAYGATE_API_PRE_AUTH));
          case CreditCard:
            validatePayData(bankCard);
            return this.CreditCardPayment(order, bankCard, apiUrl(Constants.PAYGATE_API_PRE_AUTH));
          case RedirectPay:
            validatePayData(bankCard);
            return this.ThreeDSecurePayment(order, bankCard, apiUrl(Constants.PAYGATE_API_PRE_AUTH));
          case MerchantHost:
            return this.MerchantHostPayment(order, BankCardCompatibleDTO.valueOf(bankCard), apiUrl(Constants.PAYGATE_API_PRE_AUTH));
          default:
            return null;
        }
      case preauthcc:
      case preauthcomplete:
      case preauthcancel:
        return this.PreAuthFinish(order);
      default:
        return null;
    }
  }

  //===========================================
  // Enroll API
  //===========================================

  public final Object enroll(EnrollOrder order) throws UqpayRSAException, UqpayResultVerifyException, UqpayPayFailException, IOException {
    order.setTradeType(UqpayTradeType.enroll);
    validatePayData(order);
    PayMethodEnum scenes = PayMethodEnum.valueOf(order.getMethodId());
    switch (scenes.getScenes()) {
      case MerchantHost:
        return this.EnrollCard(order);
      default:
        return null;
    }
  }

  public final Object verify(VerifyOrder order) throws UqpayRSAException, UqpayResultVerifyException, UqpayPayFailException, IOException {
    order.setTradeType(UqpayTradeType.verifycode);
    validatePayData(order);
    return this.VerifyPhone(order);
  }

  //===========================================
  // Order Options API
  //===========================================

  public final RefundResult refund(OrderRefund refund) throws IOException, UqpayRSAException, UqpayResultVerifyException, UqpayPayFailException {
    validateRequestParams(refund, "refund request data invalid for uqpay order operation");
    Map<String, String> paramsMap = PayUtil.params2Map(refund, this.auth);
    return directPost(paramsMap, apiUrl(Constants.PAYGATE_API_REFUND), RefundResult.class);
  }

  public final CancelResult cancel(OrderCancel cancel) throws UqpayRSAException, IOException, UqpayResultVerifyException, UqpayPayFailException {
    validateRequestParams(cancel, "cancel payment request data invalid for uqpay order operation");
    Map<String, String> paramsMap = PayUtil.params2Map(cancel, this.auth);
    return directPost(paramsMap, apiUrl(Constants.PAYGATE_API_CANCEL), CancelResult.class);
  }

  public final QueryResult query(OrderQuery query) throws UqpayRSAException, IOException, UqpayResultVerifyException, UqpayPayFailException {
    validateRequestParams(query, "query request data invalid for uqpay order operation");
    Map<String, String> paramsMap = PayUtil.params2Map(query, this.auth);
    return directPost(paramsMap, apiUrl(Constants.PAYGATE_API_QUERY), QueryResult.class);
  }

  public static class Builder {
    PaygateConfig paygateConfig;
    MerchantConfig merchantConfig;

    public Builder() {
    }

    public Builder paygateConfig(PaygateConfig config) {
      if (config == null) throw new NullPointerException("uqpay paygate config == null");
      if (config.getRSA() == null) throw new NullPointerException("uqpay paygate config miss rsa config");
      this.paygateConfig = config;
      return this;
    }

    public Builder merchantConfig(MerchantConfig config) {
      if (config == null) throw new NullPointerException("uqpay merchant config == null");
      if (config.getId() == 0) throw new NullPointerException("uqpay merchant config miss merchant account id");
      this.merchantConfig = config;
      return this;
    }

    public UqpayPaygate build() {
      return new UqpayPaygate(this);
    }
  }
}
