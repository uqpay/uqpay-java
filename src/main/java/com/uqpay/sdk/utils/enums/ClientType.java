package com.uqpay.sdk.utils.enums;

import com.uqpay.sdk.utils.HasValue;

public enum ClientType implements HasValue {

  PC_WEB((short)1),
  IOS((short)2),
  Android((short)3);

  private Short value;

  ClientType() {}

  ClientType(Short value) {
    this.value = value;
  }

  public short getValue() {
    return value;
  }

  public static ClientType valueOf(short value) {
    for (ClientType val : ClientType.values()) {
      if (value == val.getValue()) {
        return val;
      }
    }
    return null;
  }
}
