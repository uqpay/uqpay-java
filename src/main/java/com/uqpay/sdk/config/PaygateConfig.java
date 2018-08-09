package com.uqpay.sdk.config;

public class PaygateConfig extends BaseConfig {
  private static final long serialVersionUID = 5623251537981060230L;

  private String apiRoot = "https://paygate.uqpay.com";

  private RSAConfig uqpayPublicKey;

  public RSAConfig getUqpayPublicKey() {
    return uqpayPublicKey;
  }

  public void setUqpayPublicKey(RSAConfig uqpayPublicKey) {
    this.uqpayPublicKey = uqpayPublicKey;
  }

  public String getApiRoot() {
    return apiRoot;
  }

  public void setApiRoot(String apiRoot) {
    this.apiRoot = apiRoot;
  }
}
