package com.uqpay.sdk.config;

import java.io.Serializable;

public class BaseConfig implements Serializable {
  private static final long serialVersionUID = 3560080745805474798L;

  private boolean testMode = false;

  private RSAConfig productRSA;

  private RSAConfig testRSA;

  public RSAConfig getRSA() {
    if (isTestMode()) {
      return testRSA;
    }
    return productRSA;
  }

  public RSAConfig getProductRSA() {
    return productRSA;
  }

  public void setProductRSA(RSAConfig productRSA) {
    this.productRSA = productRSA;
  }

  public RSAConfig getTestRSA() {
    return testRSA;
  }

  public void setTestRSA(RSAConfig testRSA) {
    this.testRSA = testRSA;
  }

  public boolean isTestMode() {
    return testMode;
  }

  public void setTestMode(boolean testMode) {
    this.testMode = testMode;
  }
}
