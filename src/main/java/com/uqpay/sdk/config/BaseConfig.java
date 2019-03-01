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

  private SecureConfig productSecure;

  private SecureConfig testSecure;

  /**
   * <p>getSecure.</p>
   *
   * @return a {@link SecureConfig} object.
   */
  public SecureConfig getSecure() {
    if (isTestMode()) {
      return testSecure;
    }
    return productSecure;
  }

  /**
   * <p>Getter for the field <code>productSecure</code>.</p>
   *
   * @return a {@link SecureConfig} object.
   */
  public SecureConfig getProductSecure() {
    return productSecure;
  }

  /**
   * <p>Setter for the field <code>productSecure</code>.</p>
   *
   * @param productSecure a {@link SecureConfig} object.
   */
  public void setProductSecure(SecureConfig productSecure) {
    this.productSecure = productSecure;
  }

  /**
   * <p>Getter for the field <code>testSecure</code>.</p>
   *
   * @return a {@link SecureConfig} object.
   */
  public SecureConfig getTestSecure() {
    return testSecure;
  }

  /**
   * <p>Setter for the field <code>testSecure</code>.</p>
   *
   * @param testSecure a {@link SecureConfig} object.
   */
  public void setTestSecure(SecureConfig testSecure) {
    this.testSecure = testSecure;
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
