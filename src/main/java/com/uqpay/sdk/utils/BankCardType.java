package com.uqpay.sdk.utils;

public enum BankCardType {
  Debit((short) 1),//借记卡
  Credit((short) 2),; //信用卡

  private short value;

  BankCardType() {
  }

  BankCardType(short value) {
    this.value = value;
  }

  public short getValue() {
    return value;
  }
}
