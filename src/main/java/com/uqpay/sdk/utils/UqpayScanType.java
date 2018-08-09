package com.uqpay.sdk.utils;

public enum UqpayScanType {
  Merchant((short) 0), // merchant scan consumer
  Consumer((short) 1); // consumer scan merchant

  private Short value;

  UqpayScanType(short value) { this.value = value; }
  public Short getValue() {
    return value;
  }

  public static UqpayScanType fromValue(short value) {
    for (UqpayScanType val: UqpayScanType.values()) {
      if (value == val.getValue()) {
        return val;
      }
    }
    return null;
  }
}
