package com.uqpay.sdk.exception;

import java.util.Map;

public class UqpayResultVerifyException extends Exception {
  private Map<String, String> resultMap;
  public UqpayResultVerifyException() {
    super();
  }
  public UqpayResultVerifyException(String msg, Map<String, String> resultMap) {
    super(msg);
    this.resultMap = resultMap;
  }

  public Map<String, String> getResultMap() {
    return resultMap;
  }
}
