package com.uqpay.sdk.hostui;

import com.uqpay.sdk.UQPay;
import com.uqpay.sdk.bean.ApiResponse;
import com.uqpay.sdk.config.*;
import com.uqpay.sdk.exception.UqpayPayFailException;
import com.uqpay.sdk.hostui.bean.HostPreInitResult;
import com.uqpay.sdk.hostui.bean.HostUIInit;
import com.uqpay.sdk.exception.UqpayRSAException;
import com.uqpay.sdk.exception.UqpayResultVerifyException;
import com.uqpay.sdk.payment.bean.v1.PayOrder;
import com.uqpay.sdk.payment.bean.v1.TransResult;
import com.uqpay.sdk.utils.Constants;
import com.uqpay.sdk.utils.PayUtil;
import com.uqpay.sdk.utils.enums.UqpayTransType;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class HostUI {

  private UQPay uqPay;

  private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

  private <T> void validateRequestParams(T object, String msg) {
    if (object == null) throw new ConstraintViolationException(msg, null);
    Set<ConstraintViolation<T>> violations = validator.validate(object);
    if (violations.size() > 0) {
      throw new ConstraintViolationException(msg, violations);
    }
  }

  public HostUI(UQPay uqPay, Map<EnvEnum, String> apiAddresses) {
    this.uqPay = uqPay;
  }

  public HostUI(UQPay uqPay) {
    this.uqPay = uqPay;
  }

  //===========================================
  // Host UI
  //===========================================

  public HostPreInitResult clientToken(HostUIInit preInit) throws UqpayRSAException, UqpayResultVerifyException, IOException, UqpayPayFailException {
    validateRequestParams(preInit, "request data invalid for host pre-init");
    return uqPay.request(preInit, uqPay.getAppUrl(Constants.APPGATE_API_HOST_INIT), HostPreInitResult.class);
  }

  public ApiResponse<TransResult> pay(PayOrder order) throws UqpayRSAException, UqpayResultVerifyException, IOException, UqpayPayFailException {
    order.setTradeType(UqpayTransType.pay);
    validateRequestParams(order, "request data invalid for host ui payment");
    validateRequestParams(order.getHostPayDTO(), "request data invalid for host ui payment");
    Map<String, String> paramsMap = PayUtil.params2Map(order, order.getHostPayDTO());
    return uqPay.request(paramsMap, uqPay.getPayUrl(Constants.PAYGATE_API_HOST_PAY), TransResult.class);
  }

}
