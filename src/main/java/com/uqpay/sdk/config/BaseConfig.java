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

  private SecureConfig productRSA;

  private SecureConfig testRSA;

  /**
   * <p>getRSA.</p>
   *
   * @return a {@link SecureConfig} object.
   */
  public SecureConfig getRSA() {
    if (isTestMode()) {
      return testRSA;
    }
    return productRSA;
  }

  /**
   * <p>Getter for the field <code>productRSA</code>.</p>
   *
   * @return a {@link SecureConfig} object.
   */
  public SecureConfig getProductRSA() {
    return productRSA;
  }

  /**
   * <p>Setter for the field <code>productRSA</code>.</p>
   *
   * @param productRSA a {@link SecureConfig} object.
   */
  public void setProductRSA(SecureConfig productRSA) {
    this.productRSA = productRSA;
  }

  /**
   * <p>Getter for the field <code>testRSA</code>.</p>
   *
   * @return a {@link SecureConfig} object.
   */
  public SecureConfig getTestRSA() {
    return testRSA;
  }

  /**
   * <p>Setter for the field <code>testRSA</code>.</p>
   *
   * @param testRSA a {@link SecureConfig} object.
   */
  public void setTestRSA(SecureConfig testRSA) {
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
