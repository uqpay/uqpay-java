package com.uqpay.sdk.operation.bean;

public class ExternalExchangeDTO extends BaseJsonRequestDTO {

  /**
   * 原始币种
   * Currency to convert
   */
  private String originalCurrency;
  /**
   * 原始币种金额
   * the amount use for original currency
   */
  private Double costAmount;
  /**
   * 目标币种
   * Currency convert to
   */
  private String targetCurrency;
  /**
   * 目标币种金额
   * the amount for the target currency
   */
  private Double amount;
  /**
   * 使用的汇率
   * the exchange rate use for this convert
   */
  private Double exchangeRate;


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

  public Double getAmount() {
    return amount;
  }

  public void setAmount(Double amount) {
    this.amount = amount;
  }

  public Double getExchangeRate() {
    return exchangeRate;
  }

  public void setExchangeRate(Double exchangeRate) {
    this.exchangeRate = exchangeRate;
  }

  public Double getCostAmount() {
    return costAmount;
  }

  public void setCostAmount(Double costAmount) {
    this.costAmount = costAmount;
  }
}
