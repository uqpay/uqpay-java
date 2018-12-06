package com.uqpay.sdk.utils.enums;

public enum SignTypeEnum {
  RSA((short)1),
  MD5((short)2);
  private short value;

  SignTypeEnum(short value) {
    this.value = value;
  }

  public short getValue() {
    return value;
  }

  public static SignTypeEnum valueOf(short value){
    for (SignTypeEnum val : SignTypeEnum.values()) {
      if (value == val.getValue()) {
        return val;
      }
    }
    return null;
  }
}
