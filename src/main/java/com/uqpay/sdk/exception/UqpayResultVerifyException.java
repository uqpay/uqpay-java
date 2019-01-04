package com.uqpay.sdk.exception;

import java.util.Map;

public class UqpayResultVerifyException extends Exception {
  private Map<String, String> resultMap;
  private String jsonData;
  public UqpayResultVerifyException() {
    super();
  }
  public UqpayResultVerifyException(String msg, Map<String, String> resultMap) {
    super(msg);
    this.resultMap = resultMap;
  }
  public UqpayResultVerifyException(String msg, String jsonData) {
    super(msg);
    this.jsonData = jsonData;
  }
  public Map<String, String> getResultMap() {
    return resultMap;
  }

  public String getJsonData() {
    return jsonData;
  }
}
