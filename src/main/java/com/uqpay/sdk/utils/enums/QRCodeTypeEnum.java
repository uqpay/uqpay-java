package com.uqpay.sdk.utils.enums;

public enum QRCodeTypeEnum {
  Static((short) 11),
  Dynamic((short) 12);

  private short value;

  QRCodeTypeEnum(short value) {
    this.value = value;
  }

  public short getValue() {
    return value;
  }

  public static QRCodeTypeEnum valueOf(short value){
    for (QRCodeTypeEnum val : QRCodeTypeEnum.values()) {
      if (value == val.getValue()) {
        return val;
      }
    }
    return null;
  }
}
