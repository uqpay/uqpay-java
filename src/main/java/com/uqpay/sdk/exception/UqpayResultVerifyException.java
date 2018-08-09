package com.uqpay.sdk.exception;

import java.util.Map;

public class UqpayResultVerifyException extends Exception {
  private Map<String, Object> resultMap;
  public UqpayResultVerifyException() {
    super();
  }
  public UqpayResultVerifyException(String msg, Map<String, Object> resultMap) {
    super(msg);
    this.resultMap = resultMap;
  }

  public Map<String, Object> getResultMap() {
    return resultMap;
  }
}
