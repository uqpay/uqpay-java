package com.uqpay.sdk;

import com.uqpay.sdk.config.AppgateConfig;
import com.uqpay.sdk.config.CashierConfig;
import com.uqpay.sdk.dto.PayOptions;
import com.uqpay.sdk.dto.PaygateParams;
import com.uqpay.sdk.dto.common.*;
import com.uqpay.sdk.dto.emvco.EmvcoCreateDTO;
import com.uqpay.sdk.dto.emvco.EmvcoGetPayloadDTO;
import com.uqpay.sdk.dto.enroll.EnrollOrder;
import com.uqpay.sdk.dto.enroll.VerifyOrder;
import com.uqpay.sdk.dto.common.MerchantHostDTO;
import com.uqpay.sdk.dto.exchangeRate.ExchangeRateQueryDTO;
import com.uqpay.sdk.dto.merchant.MerchantRegisterDTO;
import com.uqpay.sdk.dto.pay.PayOrder;
import com.uqpay.sdk.dto.common.ServerHostDTO;
import com.uqpay.sdk.dto.preAuth.PreAuthOrder;
import com.uqpay.sdk.dto.result.appgate.BaseAppgateResult;
import com.uqpay.sdk.dto.result.appgate.ExchangeRateResult;
import com.uqpay.sdk.dto.result.appgate.PayloadResult;
import com.uqpay.sdk.dto.result.appgate.QRCodeResult;
import com.uqpay.sdk.exception.UqpayPayFailException;
import com.uqpay.sdk.utils.enums.PayMethodEnum;
import com.uqpay.sdk.utils.enums.PaymentSupportClient;
import com.uqpay.sdk.utils.enums.UqpayScanType;
import com.uqpay.sdk.utils.enums.UqpayTradeType;
import com.uqpay.sdk.vo.UqpayCashier;
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
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class UqpayAPI {

  private PaygateConfig paygateConfig;

  private MerchantConfig merchantConfig;

  private CashierConfig cashierConfig;

  private AppgateConfig appgateConfig;

  private AuthDTO auth;

  private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

  private <T> void validateRequestParams(T object, String msg) {
    if (object == null) throw new ConstraintViolationException(msg, null);
    Set<ConstraintViolation<T>> violations = validator.validate(object);
    if (violations.size() > 0) {
      throw new ConstraintViolationException(msg, violations);
    }
  }

  private void validatePayData(PaygateParams... params) {
    Arrays.asList(params).forEach(paygateParams -> validateRequestParams(paygateParams, "pay data invalid for uqpay payment"));
  }

  UqpayAPI(Builder builder) {
    this.paygateConfig = builder.paygateConfig;
    this.merchantConfig = builder.merchantConfig;
    this.appgateConfig = builder.appgateConfig;
    this.cashierConfig = builder.cashierConfig;
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
  public AppgateConfig appgateConfig() {
    return this.appgateConfig;
  }
  public CashierConfig cashierConfig() {
    return this.cashierConfig;
  }
  /**
   * use for partner 2 set merchantId, after build UQPAY
   * @param merchantId
   */
  public void updateMerchantId (int merchantId) {
    this.auth.setMerchantId(merchantId);
  }

  public String paygateApiUrl(String url) {
    return paygateConfig.getApiRoot() + url;
  }

  public String appgateApiUrl(String url) {
    return appgateConfig.getApiRoot() + url;
  }

  private void setAuthForJsonParams(BaseJsonRequestDTO jsonParams) {
    jsonParams.setMerchantId(this.auth.getMerchantId());
    jsonParams.setAgentId(this.auth.getAgentId());
  }

  private <T> T directFormPost(Map<String, String> paramsMap, String url, Class<T> resultClass) throws IOException, UqpayRSAException, UqpayResultVerifyException, UqpayPayFailException {
    Request request = PayUtil.generateFormRequest(PayUtil.signParams(paramsMap, paygateConfig), url);
    Response response = PayUtil.doSyncRequest(request);
    Map<String, String> resultMap = Tools.json2map(response.body().string());
    T result = PayUtil.map2Params(resultClass, resultMap);
    PayUtil.verifyUqpayNotice(resultMap, paygateConfig);
    return result;
  }

  private TransResult redirectFormPost(Map<String, String> paramsMap, String url) throws IOException, UqpayRSAException, UqpayPayFailException {
    Request request = PayUtil.generateFormRequest(PayUtil.signParams(paramsMap, paygateConfig), url);
    Response response = PayUtil.doSyncRequest(request);
    TransResult transResult = new TransResult();
    transResult.setRedirectHtml(response.body().string());
    return transResult;
  }

  private <T> T directJsonPost(Object params, Class<T> resultClass, String url) throws IOException, UqpayRSAException, UqpayResultVerifyException, UqpayPayFailException {
    ((BaseJsonRequestDTO) params).setSignature(PayUtil.signParams(params, appgateConfig));
    Request request = PayUtil.generateJsonRequest(params, url);
    Response response = PayUtil.doSyncRequest(request);
    T result = Tools.json2Obj(response.body().string(), resultClass);
    return result;
  }

  /****
    Payment
  ****/

  private final TransResult QRCodePayment(PaygateParams pay, String url) throws IOException, UqpayRSAException, UqpayResultVerifyException, UqpayPayFailException {
    PayOptions payOptions = (PayOptions) pay;
    if (payOptions.getScanType() == null) throw new NullPointerException("uqpay qr code payment need Scan Type");
    if (payOptions.getScanType().equals(UqpayScanType.Merchant) && payOptions.getIdentity() == null)
      throw new NullPointerException("uqpay qr code payment need the identity data when scan type is merchant");
    Map<String, String> paramsMap = PayUtil.params2Map(pay, this.auth);
    return directFormPost(paramsMap, url, TransResult.class);
  }

  private final TransResult OfflineQRCodePayment(PaygateParams pay, String url) throws IOException, UqpayRSAException, UqpayResultVerifyException, UqpayPayFailException {
    PayOptions payOptions = (PayOptions) pay;
    if (payOptions.getIdentity() == null)
      throw new NullPointerException("uqpay offline qr code payment need the identity data");
    if (payOptions.getMerchantCity() == null) {
      throw new NullPointerException("uqpay offline qr code payment need the merchant city data");
    }
    if (payOptions.getTerminalID() == null) {
      throw new NullPointerException("uqpay offline qr code payment need the terminal id data");
    }
    Map<String, String> paramsMap = PayUtil.params2Map(pay, this.auth);
    return directFormPost(paramsMap, url, TransResult.class);
  }

  private final TransResult RedirectPayment(PaygateParams pay, String url) throws IOException, UqpayRSAException, UqpayPayFailException {
    PayOptions payOptions = (PayOptions) pay;
    if (payOptions.getReturnUrl() == null || payOptions.getReturnUrl().equals(""))
      throw new NullPointerException("uqpay online payment need sync notice url");
    Map<String, String> paramsMap = PayUtil.params2Map(pay, this.auth);
    return redirectFormPost(paramsMap, url);
  }

  private final TransResult CreditCardPayment(PaygateParams pay, BankCardExtendDTO bankCard, String url) throws IOException, UqpayRSAException, UqpayResultVerifyException, UqpayPayFailException {
    Map<String, String> paramsMap = PayUtil.params2Map(pay, bankCard, this.auth);
    return directFormPost(paramsMap, url, TransResult.class);
  }

  private final TransResult MerchantHostPayment(PaygateParams pay, MerchantHostDTO hostDTO, String url) throws UqpayRSAException, UqpayResultVerifyException, UqpayPayFailException, IOException {
    Map<String, String> paramsMap = PayUtil.params2Map(pay, hostDTO, this.auth);
    return directFormPost(paramsMap, url, TransResult.class);
  }

  private final TransResult ServerHostPayment(PaygateParams pay, ServerHostDTO hostDTO, String url) throws UqpayRSAException, UqpayResultVerifyException, UqpayPayFailException, IOException {
    Map<String, String> paramsMap = PayUtil.params2Map(pay, hostDTO, this.auth);
    return directFormPost(paramsMap, url, TransResult.class);
  }

  private final TransResult ThreeDSecurePayment(PaygateParams pay, BankCardExtendDTO bankCard, String url) throws IOException, UqpayRSAException, UqpayPayFailException {
    PayOptions payOptions = (PayOptions) pay;
    if (payOptions.getReturnUrl() == null || payOptions.getReturnUrl().equals(""))
      throw new NullPointerException("uqpay 3D secure payment need sync notice url");
    Map<String, String> paramsMap = PayUtil.params2Map(pay, bankCard, this.auth);
    return redirectFormPost(paramsMap, url);
  }

  private final TransResult InAppPayment(PaygateParams pay, String url) throws IOException, UqpayRSAException, UqpayResultVerifyException, UqpayPayFailException {
    if (((PayOptions) pay).getClient().equals(PaymentSupportClient.PC_WEB))
      throw new NullPointerException("uqpay in-app payment not support pc client");
    Map<String, String> paramsMap = PayUtil.params2Map(pay, this.auth);
    return directFormPost(paramsMap, url, TransResult.class);
  }

  private final TransResult PreAuthFinish(PreAuthOrder order) throws UqpayRSAException, UqpayResultVerifyException, UqpayPayFailException, IOException {
    if (order.getUqOrderId() <= 0) {
      throw new NullPointerException("uqpay order id is required");
    }
    Map<String, String> paramsMap = PayUtil.params2Map(order, this.auth);
    return directFormPost(paramsMap, paygateApiUrl(Constants.PAYGATE_API_PRE_AUTH), TransResult.class);
  }

  /****
   Enroll
   ****/

  private final EnrollResult EnrollCard(EnrollOrder order) throws UqpayRSAException, UqpayResultVerifyException, UqpayPayFailException, IOException {
    Map<String, String> paramsMap = PayUtil.params2Map(order, this.auth);
    return directFormPost(paramsMap, paygateApiUrl(Constants.PAYGATE_API_ENROLL), EnrollResult.class);
  }

  private final VerifyResult VerifyPhone(VerifyOrder order) throws UqpayRSAException, UqpayResultVerifyException, UqpayPayFailException, IOException {
    Map<String, String> paramsMap = PayUtil.params2Map(order, this.auth);
    return directFormPost(paramsMap, paygateApiUrl(Constants.PAYGATE_API_VERIFY), VerifyResult.class);
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
  public final TransResult pay(PayOrder order) throws UqpayRSAException, UqpayResultVerifyException, UqpayPayFailException, IOException {
    order.setTradeType(UqpayTradeType.pay);
    validatePayData(order);
    PayMethodEnum scenes = PayMethodEnum.valueOf(order.getMethodId());
    switch (scenes.getScenes()) {
      case QRCode:
        return this.QRCodePayment(order, paygateApiUrl(Constants.PAYGATE_API_PAY));
      case OfflineQRCode:
        return this.OfflineQRCodePayment(order, paygateApiUrl(Constants.PAYGATE_API_PAY));
      case RedirectPay:
        return this.RedirectPayment(order, paygateApiUrl(Constants.PAYGATE_API_PAY));
      case InApp:
        return this.InAppPayment(order, paygateApiUrl(Constants.PAYGATE_API_PAY));
      case CreditCard:
        switch (order.getMethodId()) {
          case PayMethod.AMEX:
          case PayMethod.JCB:
          case PayMethod.Master:
          case PayMethod.VISA:
            validatePayData(order.getBankCard());
            break;
          default:
            validatePayData(BankCardDTO.valueOf(order.getBankCard()));
        }
        return this.CreditCardPayment(order, order.getBankCard(), paygateApiUrl(Constants.PAYGATE_API_PAY));
      case ThreeDCreditCard:
        validatePayData(order.getBankCard());
        return this.ThreeDSecurePayment(order, order.getBankCard(), paygateApiUrl(Constants.PAYGATE_API_PAY));
      case MerchantHost:
        validatePayData(order.getMerchantHost());
        return this.MerchantHostPayment(order, order.getMerchantHost(), paygateApiUrl(Constants.PAYGATE_API_PAY));
      case ServerHost:
        validatePayData(order.getServerHost());
        return this.ServerHostPayment(order, order.getServerHost(), paygateApiUrl(Constants.PAYGATE_API_PAY));
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
  public final TransResult preAuth(PreAuthOrder order, BankCardExtendDTO bankCard) throws UqpayRSAException, UqpayResultVerifyException, UqpayPayFailException, IOException {
    validatePayData(order);
    switch (order.getTradeType()) {
      case preauth:
        PayMethodEnum scenes = PayMethodEnum.valueOf(order.getMethodId());
        switch (scenes.getScenes()) {
          case InApp:
            return this.InAppPayment(order, paygateApiUrl(Constants.PAYGATE_API_PRE_AUTH));
          case CreditCard:
            validatePayData(bankCard);
            return this.CreditCardPayment(order, bankCard, paygateApiUrl(Constants.PAYGATE_API_PRE_AUTH));
          case RedirectPay:
            validatePayData(bankCard);
            return this.ThreeDSecurePayment(order, bankCard, paygateApiUrl(Constants.PAYGATE_API_PRE_AUTH));
          case MerchantHost:
            validatePayData(order.getMerchantHost());
            return this.MerchantHostPayment(order, order.getMerchantHost(), paygateApiUrl(Constants.PAYGATE_API_PRE_AUTH));
          case ServerHost:
            validatePayData(order.getServerHost());
            return this.ServerHostPayment(order, order.getServerHost(), paygateApiUrl(Constants.PAYGATE_API_PRE_AUTH));
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

  public final EnrollResult enroll(EnrollOrder order) throws UqpayRSAException, UqpayResultVerifyException, UqpayPayFailException, IOException {
    order.setTradeType(UqpayTradeType.enroll);
    validatePayData(order);
    PayMethodEnum scenes = PayMethodEnum.valueOf(order.getMethodId());
    switch (scenes.getScenes()) {
      case MerchantHost:
        return this.EnrollCard(order);
      case ServerHost:
        return this.EnrollCard(order);
      default:
        return null;
    }
  }

  public final VerifyResult verify(VerifyOrder order) throws UqpayRSAException, UqpayResultVerifyException, UqpayPayFailException, IOException {
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
    return directFormPost(paramsMap, paygateApiUrl(Constants.PAYGATE_API_REFUND), RefundResult.class);
  }

  public final CancelResult cancel(OrderCancel cancel) throws UqpayRSAException, IOException, UqpayResultVerifyException, UqpayPayFailException {
    validateRequestParams(cancel, "cancel payment request data invalid for uqpay order operation");
    Map<String, String> paramsMap = PayUtil.params2Map(cancel, this.auth);
    return directFormPost(paramsMap, paygateApiUrl(Constants.PAYGATE_API_CANCEL), CancelResult.class);
  }

  public final QueryResult query(OrderQuery query) throws UqpayRSAException, IOException, UqpayResultVerifyException, UqpayPayFailException {
    validateRequestParams(query, "query request data invalid for uqpay order operation");
    Map<String, String> paramsMap = PayUtil.params2Map(query, this.auth);
    return directFormPost(paramsMap, paygateApiUrl(Constants.PAYGATE_API_QUERY), QueryResult.class);
  }

  //===========================================
  // Merchant Register API
  //===========================================

  public final BaseAppgateResult register(MerchantRegisterDTO registerDTO) throws UqpayRSAException, UqpayResultVerifyException, UqpayPayFailException, IOException {
    this.setAuthForJsonParams(registerDTO);
    validateRequestParams(registerDTO, "request data invalid for uqpay register merchant");
    return directJsonPost(registerDTO, BaseAppgateResult.class, appgateApiUrl(Constants.APPGATE_API_REGISTER));
  }

  //===========================================
  // EMVCO QRCode API
  //===========================================

  public final QRCodeResult createQRCode(EmvcoCreateDTO createDTO) throws UqpayRSAException, UqpayResultVerifyException, UqpayPayFailException, IOException {
    this.setAuthForJsonParams(createDTO);
    validateRequestParams(createDTO, "request data invalid for create qr code");
    return directJsonPost(createDTO, QRCodeResult.class, appgateApiUrl(Constants.APPGATE_API_EMVCO_CREATE));
  }

  public final PayloadResult getQRCodePayload(EmvcoGetPayloadDTO payloadDTO) throws UqpayRSAException, UqpayResultVerifyException, UqpayPayFailException, IOException {
    this.setAuthForJsonParams(payloadDTO);
    validateRequestParams(payloadDTO, "request data invalid for get qr code payload");
    return directJsonPost(payloadDTO, PayloadResult.class, appgateApiUrl(Constants.APPGATE_API_EMVCO_PAYLOAD));
  }

  //===========================================
  // UQPAY Public Resource API
  //===========================================

  public final ExchangeRateResult queryExchangeRate(ExchangeRateQueryDTO queryDTO) throws UqpayRSAException, UqpayResultVerifyException, UqpayPayFailException, IOException {
    this.setAuthForJsonParams(queryDTO);
    validateRequestParams(queryDTO, "request data invalid for query exchange rate");
    return directJsonPost(queryDTO, ExchangeRateResult.class, appgateApiUrl(Constants.APPGATE_API_RES_EXCHANGE_RATE));
  }

  //===========================================
  // Cashier API
  //===========================================
  @Deprecated
  public final String generateCashierLink(UqpayCashier cashier)
      throws UnsupportedEncodingException, UqpayRSAException {
    Map<String, String> paramsMap = new HashMap<>();
    paramsMap.putAll(cashier.getParamsMap());
    String paramsQuery = Tools.stringify(paramsMap, false);
    String sign = RSAUtil.sign(paramsQuery, cashierConfig.getRSA().getPrivateKey());
    paramsMap.put(Constants.AUTH_SIGN, sign);
    return cashierConfig.getApiRoot() + "?" + Tools.stringify(paramsMap, true);
  }

  public static class Builder {
    PaygateConfig paygateConfig;
    MerchantConfig merchantConfig;
    AppgateConfig appgateConfig;
    CashierConfig cashierConfig;

    public Builder() {
    }

    public Builder paygateConfig(PaygateConfig config) {
      if (config == null) throw new NullPointerException("uqpay paygate config == null");
      if (config.getRSA() == null) throw new NullPointerException("uqpay paygate config miss rsa config");
      this.paygateConfig = config;
      return this;
    }

    public Builder merchantConfig(MerchantConfig config) {
      if (config == null) throw new NullPointerException("uqpay merchant config is null");
      if (config.getId() == 0 && config.getAgentId() == 0) throw new NullPointerException("uqpay merchant config miss merchant or agent account id");
      this.merchantConfig = config;
      return this;
    }

    public Builder appgateConfig(AppgateConfig config) {
      if (config == null) throw new NullPointerException("uqpay appgate config == null");
      if (config.getRSA() == null) throw new NullPointerException("uqpay appgate config miss rsa config");
      this.appgateConfig = config;
      return this;
    }

    public Builder cashierConfig(CashierConfig config) {
      if (config == null) throw new NullPointerException("uqpay cashier config == null");
      if (config.getRSA() == null) throw new NullPointerException("uqpay cashier config miss rsa config");
      this.cashierConfig = config;
      return this;
    }

    public UqpayAPI build() {
      if (merchantConfig == null) {
        throw new NullPointerException("uqpay merchant config is null");
      }
      if (appgateConfig == null && cashierConfig == null && paygateConfig == null) {
        throw new NullPointerException("uqpay operation config is missing, must set at least one of the configs");
      }
      return new UqpayAPI(this);
    }
  }
}
