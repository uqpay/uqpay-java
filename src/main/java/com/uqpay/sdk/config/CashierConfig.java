package com.uqpay.sdk.config;

public class CashierConfig extends BaseConfig {
  private static final long serialVersionUID = 836690672442942598L;

  private String apiRoot = "https://cashier.uqpay.com";

  public String getApiRoot() {
    return apiRoot;
  }

  public void setApiRoot(String apiRoot) {
    this.apiRoot = apiRoot;
  }
}
