package com.uqpay.sdk.config;

import java.io.Serializable;

/**
 * <p>MerchantConfig class.</p>
 *
 * @author zhengwei
 * @version $Id: $Id
 */
public class MerchantConfig implements Serializable {

  private static final long serialVersionUID = -312077541053714986L;

  /**
   * Merchant ID
   * Unique identification of the businesses in UQPAY system.
   */
  private int id;

  /**
   * <p>Constructor for MerchantConfig.</p>
   */
  public MerchantConfig() {}

  /**
   * <p>Getter for the field <code>id</code>.</p>
   *
   * @return a int.
   */
  public int getId() {
    return id;
  }

  /**
   * <p>Setter for the field <code>id</code>.</p>
   *
   * @param id a int.
   */
  public void setId(int id) {
    this.id = id;
  }
}
