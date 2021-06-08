package com.uqpay.sdk.operation;

import com.uqpay.sdk.UQPay;
import com.uqpay.sdk.config.SecureConfig;
import com.uqpay.sdk.exception.UqpayPayFailException;
import com.uqpay.sdk.exception.UqpayRSAException;
import com.uqpay.sdk.exception.UqpayResultVerifyException;
import com.uqpay.sdk.operation.bean.RealNameDTO;
import com.uqpay.sdk.operation.bean.result.RealNameResult;
import com.uqpay.sdk.utils.Constants;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
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
    // use UQPAY public RSA Key to encrypt the Pan IdCard and Mobile.
    SecureConfig secureConfig = uqPay.getSecureConfig();
    realNameDTO.setPan(secureConfig.encrypt(realNameDTO.getPan()));
    realNameDTO.setIdCard(secureConfig.encrypt(realNameDTO.getIdCard()));
    realNameDTO.setMobile(secureConfig.encrypt(realNameDTO.getMobile()));
    return uqPay.request(realNameDTO, uqPay.getAppUrl(Constants.APPGATE_API_SECURITY_REALNAME), RealNameResult.class);
  }

}
