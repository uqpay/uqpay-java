package com.uqpay.sdk.dto.result.appgate;

public class PayloadResult extends BaseAppgateResult {
  private static final long serialVersionUID = -7080084499373863644L;
  private String payload;
  private String currency;

  public String getPayload() {
    return payload;
  }

  public void setPayload(String payload) {
    this.payload = payload;
  }

  public String getCurrency() {
    return currency;
  }

  public void setCurrency(String currency) {
    this.currency = currency;
  }
}
