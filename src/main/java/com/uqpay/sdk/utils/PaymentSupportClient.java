package com.uqpay.sdk.utils;

public enum PaymentSupportClient implements HasValue {

  PC_WEB((short)1),
  IOS((short)2),
  Android((short)3);

  private Short value;

  PaymentSupportClient() {}

  PaymentSupportClient(Short value) {
    this.value = value;
  }

  public short getValue() {
    return value;
  }

  public static PaymentSupportClient valueOf(short value) {
    for (PaymentSupportClient val : PaymentSupportClient.values()) {
      if (value == val.getValue()) {
        return val;
      }
    }
    return null;
  }
}
