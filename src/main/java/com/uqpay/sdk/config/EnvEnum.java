package com.uqpay.sdk.config;

public enum EnvEnum {
  LOCAL(0), // this is only useful UQPAY to test
  TEST(1),
  PROD(2);
  private int value;

  EnvEnum(int value) {
    this.value = value;
  }

  public static EnvEnum nameOf(String name) {
    for (EnvEnum val : EnvEnum.values()) {
      if (val.name().equals(name)) {
        return val;
      }
    }
    return null;
  }

  public static EnvEnum valueOf(short value) {
    for (EnvEnum val : EnvEnum.values()) {
      if (value == val.getValue()) {
        return val;
      }
    }
    return null;
  }

  public int getValue() {
    return value;
  }
}
