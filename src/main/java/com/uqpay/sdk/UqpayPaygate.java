package com.uqpay.sdk;

import okhttp3.Request;
import okhttp3.Response;
import com.uqpay.sdk.config.MerchantConfig;
import com.uqpay.sdk.config.PaygateConfig;
import com.uqpay.sdk.dto.operation.OrderCancel;
import com.uqpay.sdk.dto.operation.OrderQuery;
import com.uqpay.sdk.dto.operation.OrderRefund;
import com.uqpay.sdk.dto.payment.CreditCard;
import com.uqpay.sdk.dto.payment.PayData;
import com.uqpay.sdk.dto.result.*;
import com.uqpay.sdk.exception.UqpayRSAException;
import com.uqpay.sdk.exception.UqpayResultVerifyException;
import com.uqpay.sdk.utils.*;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

public class UqpayPaygate {

  private PaygateConfig paygateConfig;

  private MerchantConfig merchantConfig;

  private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

  private <T> void validateRequestParams(T object, String msg) {
    Set<ConstraintViolation<T>> violations = validator.validate(object);
    if (violations.size() > 0) {
      throw new ConstraintViolationException(msg, violations);
    }
  }

  private void validatePayData(PayData payData) {
    validateRequestParams(payData, "pay data invalid for uqpay payment");
  }

  UqpayPaygate(Builder builder) {
    this.paygateConfig = builder.paygateConfig;
    this.merchantConfig = builder.merchantConfig;
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

  private Map<String, Object> doServerSidePost(Map<String, String> paramsMap, String url) throws IOException, UqpayRSAException, UqpayResultVerifyException {
    Request request = PayUtil.generateRequest(PayUtil.signParams(paramsMap, paygateConfig), url);
    Response response = PayUtil.doSyncFormRequest(request);
    Map<String, Object> resultMap = Tools.json2map(response.body().string());
    PayUtil.verifyUqpayNotice(resultMap, paygateConfig);
    return resultMap;
  }

  private Map<String, Object> doServerSidePostPay(Map<String, String> paramsMap) throws IOException, UqpayRSAException, UqpayResultVerifyException {
    return doServerSidePost(paramsMap, apiUrl(Constants.PAYGATE_API_PAY));
  }

  public final QRResult QRCodePayment(PayData pay) throws IOException, UqpayRSAException, UqpayResultVerifyException {
    validatePayData(pay);
    if (pay.getScanType() == null) throw new NullPointerException("uqpay qr code payment need Scan Type");
    if (pay.getScanType().equals(UqpayScanType.Merchant) && pay.getIdentity() == null) throw new NullPointerException("uqpay qr code payment need the identity data when scan type is merchant");
    Map<String, String> paramsMap = PayUtil.generateDefPayParams(pay, merchantConfig);
    paramsMap.put(Constants.PAY_OPTIONS_SCAN_TYPE, pay.getScanType().getValue().toString());
    return new QRResult(doServerSidePostPay(paramsMap));
  }

  public final String OnlinePayment(PayData pay) throws IOException, UqpayRSAException {
    validatePayData(pay);
    if (pay.getReturnUrl() == null || pay.getReturnUrl().equals(""))
      throw new NullPointerException("uqpay online payment need sync notice url");
    Map<String, String> paramsMap = PayUtil.generateDefPayParams(pay, merchantConfig);
    paramsMap.put(Constants.PAY_OPTIONS_SYNC_NOTICE_URL, pay.getReturnUrl());
    PayUtil.signParams(paramsMap, paygateConfig);
    return apiUrl(Constants.PAYGATE_API_PAY) + "?" + Tools.stringify(paramsMap, true);
  }

  private Map<String, String> generateCreditCardPayParams(CreditCard creditCard, PayData payData) {
    validateRequestParams(creditCard, "credit card info invalid for uqpay credit card payment");
    validatePayData(payData);
    Map<String, String> paramsMap = PayUtil.generateDefPayParams(payData, merchantConfig);
    paramsMap.putAll(PayUtil.generateCreditCardPayParams(creditCard));
    return paramsMap;
  }

  public final CardResult CreditCardPayment(CreditCard creditCard, PayData payData) throws IOException, UqpayRSAException, UqpayResultVerifyException {
    return new CardResult(doServerSidePostPay(generateCreditCardPayParams(creditCard, payData)));
  }

  public final String ThreeDSecurePayment(CreditCard creditCard, PayData payData) throws IOException, UqpayRSAException {
    if (payData.getReturnUrl() == null || payData.getReturnUrl().equals(""))
      throw new NullPointerException("uqpay 3D secure payment need sync notice url");
    return apiUrl(Constants.PAYGATE_API_PAY) + "?" + Tools.stringify(PayUtil.signParams(generateCreditCardPayParams(creditCard, payData), paygateConfig), true);
  }

  public final InAppResult InAppPayment(PayData payData) throws IOException, UqpayRSAException, UqpayResultVerifyException {
    if (payData.getClient() == null) throw new NullPointerException("client type is required for uqpay in-app payment");
    if (payData.getClient().equals(PaymentSupportClient.PC_WEB)) throw new NullPointerException("uqpay in-app payment not support pc client");
    validatePayData(payData);
    Map<String, String> paramsMap = PayUtil.generateDefPayParams(payData, merchantConfig);
    paramsMap.put(Constants.PAY_OPTIONS_CLIENT_TYPE, payData.getClient().getValue().toString());
    return new InAppResult(doServerSidePostPay(paramsMap));
  }

  public final RefundResult Refund(OrderRefund refund) throws IOException, UqpayRSAException, UqpayResultVerifyException {
    validateRequestParams(refund, "refund request data invalid for uqpay order operation");
    Map<String, String> paramsMap = PayUtil.generateRefundParams(refund, merchantConfig);
    return new RefundResult(doServerSidePost(paramsMap, apiUrl(Constants.PAYGATE_API_REFUND)));
  }

  public final CancelResult Cancel(OrderCancel cancel) throws UqpayRSAException, IOException, UqpayResultVerifyException {
    validateRequestParams(cancel, "cancel payment request data invalid for uqpay order operation");
    Map<String, String> paramsMap = PayUtil.generateCancelParams(cancel, merchantConfig);
    return new CancelResult(doServerSidePost(paramsMap, apiUrl(Constants.PAYGATE_API_CANCEL)));
  }

  public final QueryResult Query(OrderQuery query) throws UqpayRSAException, IOException, UqpayResultVerifyException {
    validateRequestParams(query, "query request data invalid for uqpay order operation");
    Map<String, String> paramsMap = PayUtil.generateQueryParams(query, merchantConfig);
    return new QueryResult(doServerSidePost(paramsMap, apiUrl(Constants.PAYGATE_API_QUERY)));
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
