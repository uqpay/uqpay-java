package com.uqpay.sdk.dto.merchant;

import com.uqpay.sdk.dto.common.BaseJsonRequestDTO;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

public class ConfigPaymentDTO extends BaseJsonRequestDTO {
  private static final long serialVersionUID = -2581487655316468860L;
  @Positive
  private int methodId;
  @NotEmpty
  private String tradeCurrency;
  @NotEmpty
  private String settleCurrency;
  private int feeTempID; // fee template id, u can set a fee template on UQPAY Partner Dashboard;
  private boolean defOpen;// if true, it will be active automatic

  public int getMethodId() {
    return methodId;
  }

  public void setMethodId(int methodId) {
    this.methodId = methodId;
  }

  public String getTradeCurrency() {
    return tradeCurrency;
  }

  public void setTradeCurrency(String tradeCurrency) {
    this.tradeCurrency = tradeCurrency;
  }

  public String getSettleCurrency() {
    return settleCurrency;
  }

  public void setSettleCurrency(String settleCurrency) {
    this.settleCurrency = settleCurrency;
  }

  public int getFeeTempID() {
    return feeTempID;
  }

  public void setFeeTempID(int feeTempID) {
    this.feeTempID = feeTempID;
  }

  public boolean isDefOpen() {
    return defOpen;
  }

  public void setDefOpen(boolean defOpen) {
    this.defOpen = defOpen;
  }
}
