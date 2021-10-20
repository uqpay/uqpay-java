package com.uqpay.sdk.operation;

import com.uqpay.sdk.UQPay;
import com.uqpay.sdk.config.*;
import com.uqpay.sdk.exception.UqpayPayFailException;
import com.uqpay.sdk.operation.bean.*;
import com.uqpay.sdk.exception.UqpayRSAException;
import com.uqpay.sdk.exception.UqpayResultVerifyException;
import com.uqpay.sdk.operation.bean.result.*;
import com.uqpay.sdk.utils.Constants;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import java.io.IOException;
import java.util.Set;

public class Merchant {

  private UQPay uqPay;

  private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

  private <T> void validateRequestParams(T object, String msg) {
    if (object == null) throw new ConstraintViolationException(msg, null);
    Set<ConstraintViolation<T>> violations = validator.validate(object);
    if (violations.size() > 0) {
      throw new ConstraintViolationException(msg, violations);
    }
  }

  public Merchant(UQPay uqPay) {
    this.uqPay = uqPay;
  }

  //===========================================
  // Merchant Manager API
  //===========================================

  public final BaseAppgateResult register(MerchantRegisterDTO registerDTO) throws UqpayRSAException, UqpayResultVerifyException, IOException, UqpayPayFailException {
    validateRequestParams(registerDTO, "request data invalid for uqpay register merchant");
    return uqPay.request(registerDTO, uqPay.getAppUrl(Constants.APPGATE_API_MERCHANT_REGISTER), BaseAppgateResult.class);
  }

  public final MerchantInfoResult detail(BaseJsonRequestDTO requestDTO) throws UqpayRSAException, UqpayResultVerifyException, IOException, UqpayPayFailException {
    validateRequestParams(requestDTO, "request data invalid for query merchant detail");
    return uqPay.request(requestDTO, uqPay.getAppUrl(Constants.APPGATE_API_MERCHANT_VIEW), MerchantInfoResult.class);
  }

  public final MerchantListResult list(PageRequestDTO requestDTO) throws UqpayRSAException, UqpayResultVerifyException, IOException, UqpayPayFailException {
    validateRequestParams(requestDTO, "request data invalid for query merchant list");
    return uqPay.request(requestDTO, uqPay.getAppUrl(Constants.APPGATE_API_MERCHANT_LIST), MerchantListResult.class);
  }

  public final BaseAppgateResult withdraw(WithdrawDTO withdrawDTO) throws UqpayRSAException, UqpayResultVerifyException, IOException, UqpayPayFailException {
    validateRequestParams(withdrawDTO, "request data invalid for withdraw apply");
    return uqPay.request(withdrawDTO, uqPay.getAppUrl(Constants.APPGATE_API_FINANCE_APPLY_WITHDRAW), BaseAppgateResult.class);
  }

  public final BalanceResult queryBalance(BaseJsonRequestDTO requestDTO) throws UqpayRSAException, UqpayResultVerifyException, IOException, UqpayPayFailException {
    validateRequestParams(requestDTO, "request data invalid for query balance");
    return uqPay.request(requestDTO, uqPay.getAppUrl(Constants.APPGATE_API_FINANCE_BALANCE), BalanceResult.class);
  }

  public final ConfigMethodResult configPayMethod(ConfigPaymentDTO paymentDTO) throws UqpayRSAException, UqpayResultVerifyException, IOException, UqpayPayFailException {
    validateRequestParams(paymentDTO, "request data invalid for config merchant config");
    return uqPay.request(paymentDTO, uqPay.getAppUrl(Constants.APPGATE_API_PRODUCT_CONFIG), ConfigMethodResult.class);
  }

  public final MerchantPayMethodListResult queryConfiguredPayMethod(BaseJsonRequestDTO requestDTO) throws UqpayRSAException, UqpayResultVerifyException, IOException, UqpayPayFailException {
    validateRequestParams(requestDTO, "request data invalid for query configured pay method");
    return uqPay.request(requestDTO, uqPay.getAppUrl(Constants.APPGATE_API_PRODUCT_LIST), MerchantPayMethodListResult.class);
  }

  public final TopUpAccountResult getTopUpAccount(BaseJsonRequestDTO requestDTO) throws UqpayRSAException, UqpayResultVerifyException, IOException, UqpayPayFailException {
    validateRequestParams(requestDTO, "request data invalid for get topUp account");
    return uqPay.request(requestDTO, uqPay.getAppUrl(Constants.APPGATE_API_FINANCE_ACCOUNT_TOPUP_VIEW), TopUpAccountResult.class);
  }

}
