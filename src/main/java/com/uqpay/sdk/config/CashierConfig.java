package com.uqpay.sdk.config;

/**
 * <p>CashierConfig class.</p>
 *
 * @author zhengwei
 * @version $Id: $Id
 */
public class CashierConfig extends BaseConfig {
  private static final long serialVersionUID = 836690672442942598L;

  private String apiRoot = "https://cashier.uqpay.com";

  /**
   * <p>Getter for the field <code>apiRoot</code>.</p>
   *
   * @return a {@link java.lang.String} object.
   */
  public String getApiRoot() {
    return apiRoot;
  }

  /**
   * <p>Setter for the field <code>apiRoot</code>.</p>
   *
   * @param apiRoot a {@link java.lang.String} object.
   */
  public void setApiRoot(String apiRoot) {
    this.apiRoot = apiRoot;
  }
}
