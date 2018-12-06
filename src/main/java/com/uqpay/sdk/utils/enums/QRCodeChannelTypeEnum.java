package com.uqpay.sdk.utils.enums;

public enum QRCodeChannelTypeEnum {
  UnionPay((short) 1);
  private short value;

  QRCodeChannelTypeEnum(short value) {
    this.value = value;
  }

  public short getValue() {
    return value;
  }

  public static QRCodeChannelTypeEnum valueOf(short value) {
    for (QRCodeChannelTypeEnum val : QRCodeChannelTypeEnum.values()) {
      if (value == val.getValue()) {
        return val;
      }
    }
    return null;
  }
}
