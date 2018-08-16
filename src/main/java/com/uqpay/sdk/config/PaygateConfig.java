package com.uqpay.sdk.config;

/**
 * <p>PaygateConfig class.</p>
 *
 * @author zhengwei
 * @version $Id: $Id
 */
public class PaygateConfig extends BaseConfig {
  private static final long serialVersionUID = 5623251537981060230L;

  private String apiRoot = "https://paygate.uqpay.com";

  private RSAConfig uqpayPublicKey;

  /**
   * <p>Getter for the field <code>uqpayPublicKey</code>.</p>
   *
   * @return a {@link com.uqpay.sdk.config.RSAConfig} object.
   */
  public RSAConfig getUqpayPublicKey() {
    return uqpayPublicKey;
  }

  /**
   * <p>Setter for the field <code>uqpayPublicKey</code>.</p>
   *
   * @param uqpayPublicKey a {@link com.uqpay.sdk.config.RSAConfig} object.
   */
  public void setUqpayPublicKey(RSAConfig uqpayPublicKey) {
    this.uqpayPublicKey = uqpayPublicKey;
  }

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
