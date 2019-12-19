package com.uqpay.sdk.payment;

import com.uqpay.sdk.UQPay;
import com.uqpay.sdk.bean.ApiResponse;
import com.uqpay.sdk.payment.bean.v1.BankCardDTO;
import com.uqpay.sdk.payment.bean.v1.PaygateParams;
import com.uqpay.sdk.payment.bean.v1.PreAuthOrder;
import com.uqpay.sdk.exception.UqpayRSAException;
import com.uqpay.sdk.exception.UqpayResultVerifyException;
import com.uqpay.sdk.payment.bean.result.OnlineResult;
import com.uqpay.sdk.payment.bean.tx.OnlineTX;
import com.uqpay.sdk.payment.bean.v1.TransResult;
import com.uqpay.sdk.utils.Constants;
import com.uqpay.sdk.utils.PayMethod;
import com.uqpay.sdk.utils.PayUtil;
import com.uqpay.sdk.utils.enums.*;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;

public class PreAuth {

  private UQPay uqPay;
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

  public PreAuth(UQPay uqPay) {
    this.uqPay = uqPay;
  }

  private String getUrl() {
    return uqPay.getPayUrl(Constants.PAYGATE_API_PRE_AUTH);
  }

  /****
    Pay
  ****/

  public ApiResponse<OnlineResult> online(OnlineTX tx) throws UqpayRSAException, IOException, UqpayResultVerifyException {
    tx.setTransType(UqpayTransType.preauth);
    ApiResponse<OnlineResult> response = uqPay.request(tx, getUrl(), OnlineResult.class);
    return response;
  }

  public TransResult bankCard(PreAuthOrder order) throws IOException, UqpayRSAException, UqpayResultVerifyException {
    order.setTradeType(UqpayTransType.preauth);
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
    Map<String, String> paramsMap = PayUtil.params2Map(order, order.getBankCard());
    return uqPay.request(paramsMap, getUrl(), TransResult.class);
  }

  public TransResult inAPP(PreAuthOrder order) throws IOException, UqpayRSAException, UqpayResultVerifyException {
    order.setTradeType(UqpayTransType.preauth);
    if (order.getClient().equals(ClientType.Web))
      throw new NullPointerException("uqpay in-app payment not support pc client");
    Map<String, String> paramsMap = PayUtil.params2Map(order);
    return uqPay.request(paramsMap, getUrl(), TransResult.class);
  }

  public TransResult hostByMerchant(PreAuthOrder order) throws UqpayRSAException, UqpayResultVerifyException, IOException {
    order.setTradeType(UqpayTransType.preauth);
    validatePayData(order.getMerchantHost());
    Map<String, String> paramsMap = PayUtil.params2Map(order, order.getMerchantHost());
    return uqPay.request(paramsMap, getUrl(), TransResult.class);
  }

  public TransResult hostByServer(PreAuthOrder order) throws UqpayRSAException, UqpayResultVerifyException, IOException {
    order.setTradeType(UqpayTransType.preauth);
    validatePayData(order.getServerHost());
    Map<String, String> paramsMap = PayUtil.params2Map(order, order.getServerHost());
    return uqPay.request(paramsMap, getUrl(), TransResult.class);
  }

  public TransResult finish(PreAuthOrder order) throws UqpayRSAException, UqpayResultVerifyException, IOException {
    if (order.getUqOrderId() <= 0) {
      throw new NullPointerException("uqpay order id is required");
    }
    Map<String, String> paramsMap = PayUtil.params2Map(order);
    return uqPay.request(paramsMap, getUrl(), TransResult.class);
  }
}
