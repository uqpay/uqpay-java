package com.uqpay.sdk.operation;

import com.uqpay.sdk.UQPay;
import com.uqpay.sdk.config.*;
import com.uqpay.sdk.exception.UqpayPayFailException;
import com.uqpay.sdk.operation.bean.PageRequestDTO;
import com.uqpay.sdk.operation.bean.EmvcoCreateDTO;
import com.uqpay.sdk.operation.bean.EmvcoGetPayloadDTO;
import com.uqpay.sdk.exception.UqpayRSAException;
import com.uqpay.sdk.exception.UqpayResultVerifyException;
import com.uqpay.sdk.operation.bean.result.MerchantQRCodeListResult;
import com.uqpay.sdk.operation.bean.result.PayloadResult;
import com.uqpay.sdk.operation.bean.result.QRCodeResult;
import com.uqpay.sdk.utils.Constants;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Emvco {

  private UQPay uqPay;

  private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

  private <T> void validateRequestParams(T object, String msg) {
    if (object == null) throw new ConstraintViolationException(msg, null);
    Set<ConstraintViolation<T>> violations = validator.validate(object);
    if (violations.size() > 0) {
      throw new ConstraintViolationException(msg, violations);
    }
  }

  public Emvco(UQPay uqPay) {
    this.uqPay = uqPay;
  }

  //===========================================
  // EMVCO QRCode API
  //===========================================

  public final QRCodeResult createQRCode(EmvcoCreateDTO createDTO) throws UqpayRSAException, UqpayResultVerifyException, IOException, UqpayPayFailException {
    validateRequestParams(createDTO, "request data invalid for create qr code");
    return uqPay.request(createDTO, uqPay.getAppUrl(Constants.APPGATE_API_EMVCO_CREATE), QRCodeResult.class);
  }

  public final PayloadResult getQRCodePayload(EmvcoGetPayloadDTO payloadDTO) throws UqpayRSAException, UqpayResultVerifyException, IOException, UqpayPayFailException {
    validateRequestParams(payloadDTO, "request data invalid for get qr code payload");
    return uqPay.request(payloadDTO, uqPay.getAppUrl(Constants.APPGATE_API_EMVCO_PAYLOAD), PayloadResult.class);
  }

  public final MerchantQRCodeListResult queryAllQRCode(PageRequestDTO requestDTO) throws UqpayRSAException, UqpayResultVerifyException, IOException, UqpayPayFailException {
    validateRequestParams(requestDTO, "request data invalid for query all QRCode of merchant");
    return uqPay.request(requestDTO, uqPay.getAppUrl(Constants.APPGATE_API_EMVCO_QUERY), MerchantQRCodeListResult.class);
  }
}
