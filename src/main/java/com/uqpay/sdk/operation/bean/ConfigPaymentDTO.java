package com.uqpay.sdk.operation.bean;

import com.uqpay.sdk.operation.bean.BaseJsonRequestDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class ConfigPaymentDTO extends BaseJsonRequestDTO {
  private static final long serialVersionUID = 1125611182758962993L;
  @Positive
  private int methodId;
  private int feeTempID; // fee template id, u can set a fee template on UQPAY Partner Dashboard;
  private boolean defOpen;// if true, it will be active automatic
  @NotBlank
  private String merchantName;
  @NotBlank
  private String merchantAbbr;

  public String getMerchantName() {
    return merchantName;
  }

  public void setMerchantName(String merchantName) {
    this.merchantName = merchantName;
  }

  public String getMerchantAbbr() {
    return merchantAbbr;
  }

  public void setMerchantAbbr(String merchantAbbr) {
    this.merchantAbbr = merchantAbbr;
  }

  public int getMethodId() {
    return methodId;
  }

  public void setMethodId(int methodId) {
    this.methodId = methodId;
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
