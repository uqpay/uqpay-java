package com.uqpay.sdk.operation.bean;

import com.uqpay.sdk.operation.bean.BaseJsonRequestDTO;

import jakarta.validation.constraints.NotBlank;

public class ExchangeRateQueryDTO extends BaseJsonRequestDTO {

  @NotBlank
  private String originalCurrency;
  @NotBlank
  private String targetCurrency;

  public String getOriginalCurrency() {
    return originalCurrency;
  }

  public void setOriginalCurrency(String originalCurrency) {
    this.originalCurrency = originalCurrency;
  }

  public String getTargetCurrency() {
    return targetCurrency;
  }

  public void setTargetCurrency(String targetCurrency) {
    this.targetCurrency = targetCurrency;
  }
}
