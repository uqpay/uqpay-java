package com.uqpay.sdk.exception;

public class UqpayPayFailException extends Exception{
  private int code;
  private String message;

  public UqpayPayFailException(int code, String message) {
    this.code = code;
    this.message = message;
  }

  public int getCode() {
    return code;
  }

  @Override
  public String getMessage() {
    return message;
  }
}
