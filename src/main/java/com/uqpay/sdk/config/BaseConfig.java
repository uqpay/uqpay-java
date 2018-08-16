package com.uqpay.sdk.config;

import java.io.Serializable;

/**
 * <p>BaseConfig class.</p>
 *
 * @author zhengwei
 * @version $Id: $Id
 */
public class BaseConfig implements Serializable {
  private static final long serialVersionUID = 3560080745805474798L;

  private boolean testMode = false;

  private RSAConfig productRSA;

  private RSAConfig testRSA;

  /**
   * <p>getRSA.</p>
   *
   * @return a {@link com.uqpay.sdk.config.RSAConfig} object.
   */
  public RSAConfig getRSA() {
    if (isTestMode()) {
      return testRSA;
    }
    return productRSA;
  }

  /**
   * <p>Getter for the field <code>productRSA</code>.</p>
   *
   * @return a {@link com.uqpay.sdk.config.RSAConfig} object.
   */
  public RSAConfig getProductRSA() {
    return productRSA;
  }

  /**
   * <p>Setter for the field <code>productRSA</code>.</p>
   *
   * @param productRSA a {@link com.uqpay.sdk.config.RSAConfig} object.
   */
  public void setProductRSA(RSAConfig productRSA) {
    this.productRSA = productRSA;
  }

  /**
   * <p>Getter for the field <code>testRSA</code>.</p>
   *
   * @return a {@link com.uqpay.sdk.config.RSAConfig} object.
   */
  public RSAConfig getTestRSA() {
    return testRSA;
  }

  /**
   * <p>Setter for the field <code>testRSA</code>.</p>
   *
   * @param testRSA a {@link com.uqpay.sdk.config.RSAConfig} object.
   */
  public void setTestRSA(RSAConfig testRSA) {
    this.testRSA = testRSA;
  }

  /**
   * <p>isTestMode.</p>
   *
   * @return a boolean.
   */
  public boolean isTestMode() {
    return testMode;
  }

  /**
   * <p>Setter for the field <code>testMode</code>.</p>
   *
   * @param testMode a boolean.
   */
  public void setTestMode(boolean testMode) {
    this.testMode = testMode;
  }
}
