package com.uqpay.sdk.operation;

import com.uqpay.sdk.UQPay;
import com.uqpay.sdk.config.ResourceTypeEnum;
import com.uqpay.sdk.exception.UqpayPayFailException;
import com.uqpay.sdk.exception.UqpayRSAException;
import com.uqpay.sdk.exception.UqpayResultVerifyException;
import com.uqpay.sdk.operation.bean.DownloadCheckingFileDTO;
import com.uqpay.sdk.operation.bean.ExchangeRateQueryDTO;
import com.uqpay.sdk.operation.bean.ExternalExchangeDTO;
import com.uqpay.sdk.operation.bean.result.BaseAppgateResult;
import com.uqpay.sdk.operation.bean.result.ExchangeRateResult;
import com.uqpay.sdk.utils.Constants;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validation;
import jakarta.validation.Validator;

import java.io.IOException;
import java.util.Set;

public class InterPub {
  private UQPay uqPay;

  private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

  private <T> void validateRequestParams(T object, String msg) {
    if (object == null) throw new ConstraintViolationException(msg, null);
    Set<ConstraintViolation<T>> violations = validator.validate(object);
    if (violations.size() > 0) {
      throw new ConstraintViolationException(msg, violations);
    }
  }

  public InterPub(UQPay uqPay) {
    this.uqPay = uqPay;
  }

  //===========================================
  // UQPAY Internal Public Resource API
  //===========================================

  public final BaseAppgateResult queryExchangeRate(ExternalExchangeDTO exchange) throws UqpayRSAException, UqpayResultVerifyException, IOException, UqpayPayFailException {
    validateRequestParams(exchange, "request data invalid for external exchange");
    return uqPay.request(exchange, uqPay.getAppUrl(Constants.APPGATE_API_INTERPUB_EXCHANGE), BaseAppgateResult.class);
  }
}
