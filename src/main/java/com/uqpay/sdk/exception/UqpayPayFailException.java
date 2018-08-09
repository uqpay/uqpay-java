package com.uqpay.sdk.exception;

import com.uqpay.sdk.utils.Constants;

import java.util.Map;

public class UqpayPayFailException extends UqpayResultVerifyException{
  private int code;
  public UqpayPayFailException(String msg, Map<String, Object> resultMap) {
    super(msg, resultMap);
    this.code = Integer.valueOf(resultMap.get(Constants.RESULT_CODE).toString());
  }

  public int getCode() {
    return code;
  }
}
