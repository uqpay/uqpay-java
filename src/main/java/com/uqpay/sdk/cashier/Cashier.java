package com.uqpay.sdk.cashier;

import com.uqpay.sdk.UQPay;
import com.uqpay.sdk.config.EnvEnum;
import com.uqpay.sdk.config.MemberTypeEnum;
import com.uqpay.sdk.cashier.bean.CashierOrder;
import com.uqpay.sdk.exception.UqpayRSAException;
import com.uqpay.sdk.utils.Constants;
import com.uqpay.sdk.utils.PayUtil;
import com.uqpay.sdk.utils.Tools;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Cashier {

  private UQPay uqPay;
  private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

  private <T> void validateRequestParams(T object, String msg) {
    if (object == null) throw new ConstraintViolationException(msg, null);
    Set<ConstraintViolation<T>> violations = validator.validate(object);
    if (violations.size() > 0) {
      throw new ConstraintViolationException(msg, violations);
    }
  }

  public Cashier(UQPay uqPay) {
    this.uqPay = uqPay;
  }

  public final String address(CashierOrder order) throws UnsupportedEncodingException, UqpayRSAException {
    validateRequestParams(order, "pay data invalid for uqpay cashier");
    Map<String, String> paramsMap = PayUtil.params2Map(order);
    // set identity info
    if (uqPay.getMemberType().equals(MemberTypeEnum.AGENT)) {
      paramsMap.put(Constants.AUTH_AGENT_ID, String.valueOf(uqPay.getMemberId()));
    }
    if (uqPay.getMemberType().equals(MemberTypeEnum.MERCHANT)) {
      paramsMap.put(Constants.AUTH_MERCHANT_ID, String.valueOf(uqPay.getMemberId()));
    }
    return uqPay.getCashierUrl("") + "?" + Tools.stringify(PayUtil.signParams(paramsMap, uqPay.getSecureConfig()), true);
  }
}
