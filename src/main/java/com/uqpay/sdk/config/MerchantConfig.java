package com.uqpay.sdk.config;

import java.io.Serializable;

public class MerchantConfig implements Serializable {

  private static final long serialVersionUID = -312077541053714986L;

  /**
   * Merchant ID
   * Unique identification of the businesses in UQPAY system.
   */
  private int id;

  private int agentId;

  public MerchantConfig() {}

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getAgentId() {
    return agentId;
  }

  public void setAgentId(int agentId) {
    this.agentId = agentId;
  }
}
