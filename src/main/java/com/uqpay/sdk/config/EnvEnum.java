package com.uqpay.sdk.config;

public enum EnvEnum {
  LOCAL(0), // this is only useful UQPAY to test
  TEST(1),
  PROD(2);
  private int value;

  EnvEnum(int value) {
    this.value = value;
  }

  public int getValue() {
    return value;
  }
}
