package com.uqpay.sdk.operation;

import com.uqpay.sdk.UQPay;
import com.uqpay.sdk.config.*;
import com.uqpay.sdk.operation.bean.ExchangeRateQueryDTO;
import com.uqpay.sdk.operation.bean.DownloadCheckingFileDTO;
import com.uqpay.sdk.exception.UqpayRSAException;
import com.uqpay.sdk.exception.UqpayResultVerifyException;
import com.uqpay.sdk.operation.bean.result.ExchangeRateResult;
import com.uqpay.sdk.utils.Constants;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import java.io.IOException;
import java.util.Set;

public class Resources {

  private UQPay uqPay;

  private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

  private <T> void validateRequestParams(T object, String msg) {
    if (object == null) throw new ConstraintViolationException(msg, null);
    Set<ConstraintViolation<T>> violations = validator.validate(object);
    if (violations.size() > 0) {
      throw new ConstraintViolationException(msg, violations);
    }
  }

  public Resources(UQPay uqPay) {
    this.uqPay = uqPay;
  }

  public final void checkingFiles(DownloadCheckingFileDTO fileDTO) throws UqpayRSAException, IOException {
    validateRequestParams(fileDTO, "request data invalid for download checking file");
    uqPay.doDownload(fileDTO, ResourceTypeEnum.CHECKING_FILE, uqPay.getAppUrl(Constants.APPGATE_API_MERCHANT_CHECKING));
  }

  //===========================================
  // UQPAY Public Resource API
  //===========================================

  public final ExchangeRateResult queryExchangeRate(ExchangeRateQueryDTO queryDTO) throws UqpayRSAException, UqpayResultVerifyException, IOException {
    validateRequestParams(queryDTO, "request data invalid for query exchange rate");
    return uqPay.request(queryDTO, uqPay.getAppUrl(Constants.APPGATE_API_RES_EXCHANGE_RATE), ExchangeRateResult.class);
  }
}
