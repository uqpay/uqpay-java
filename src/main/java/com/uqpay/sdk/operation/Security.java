package com.uqpay.sdk.operation;

import com.uqpay.sdk.UQPay;
import com.uqpay.sdk.exception.UqpayPayFailException;
import com.uqpay.sdk.exception.UqpayRSAException;
import com.uqpay.sdk.exception.UqpayResultVerifyException;
import com.uqpay.sdk.operation.bean.RealNameDTO;
import com.uqpay.sdk.operation.bean.result.RealNameResult;
import com.uqpay.sdk.utils.Constants;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import java.io.IOException;
import java.util.Set;

public class Security {
  private UQPay uqPay;

  private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

  private <T> void validateRequestParams(T object, String msg) {
    if (object == null) throw new ConstraintViolationException(msg, null);
    Set<ConstraintViolation<T>> violations = validator.validate(object);
    if (violations.size() > 0) {
      throw new ConstraintViolationException(msg, violations);
    }
  }

  public Security(UQPay uqPay) {
    this.uqPay = uqPay;
  }

  public final RealNameResult realName(RealNameDTO realNameDTO) throws UqpayRSAException, UqpayResultVerifyException, UqpayPayFailException, IOException {
    validateRequestParams(realNameDTO, "request data invalid for uqpay real name valid");
    if (realNameDTO.getPan() != null && realNameDTO.getPan().length() > 0) {
      // use UQPAY public RSA Key to sign the card num.
      realNameDTO.setPan(uqPay.getSecureConfig().encrypt(realNameDTO.getPan()));
    }
    return uqPay.request(realNameDTO, uqPay.getAppUrl(Constants.APPGATE_API_SECURITY_REALNAME), RealNameResult.class);
  }

}
