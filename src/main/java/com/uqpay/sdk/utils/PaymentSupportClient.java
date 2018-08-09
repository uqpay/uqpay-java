package com.uqpay.sdk.utils;

public enum PaymentSupportClient {

  PC_WEB((short)1),
  IOS((short)2),
  Android((short)3);

  private final Short value;

  PaymentSupportClient(Short value) {
    this.value = value;
  }

  public Short getValue() {
    return value;
  }

  public static PaymentSupportClient fromShort(short value) {
    for (PaymentSupportClient val : PaymentSupportClient.values()) {
      if (value == val.getValue()) {
        return val;
      }
    }
    return null;
  }
}
