package com.uqpay.sdk.utils.enums;

import com.uqpay.sdk.utils.HasValue;

public enum UqpayScanType implements HasValue {
  Merchant((short) 0), // merchant scan consumer
  Consumer((short) 1); // consumer scan merchant

  private Short value;

  UqpayScanType() {
  }

  UqpayScanType(short value) { this.value = value; }
  public short getValue() {
    return value;
  }

  public static UqpayScanType valueOf(short value) {
    for (UqpayScanType val: UqpayScanType.values()) {
      if (value == val.getValue()) {
        return val;
      }
    }
    return null;
  }
}
