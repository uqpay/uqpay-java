package com.uqpay.sdk.dto.exchangeRate;

import com.uqpay.sdk.dto.common.BaseJsonRequestDTO;

import javax.validation.constraints.NotBlank;

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
