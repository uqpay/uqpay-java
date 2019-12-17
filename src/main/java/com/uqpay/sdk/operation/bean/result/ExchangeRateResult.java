package com.uqpay.sdk.operation.bean.result;

import java.util.Date;

public class ExchangeRateResult extends BaseAppgateResult {
  private static final long serialVersionUID = 5840052371478198126L;
  private String originalCurrency; // 原币种
  private String targetCurrency; // 目标币种
  private double buyPrice; // 买入价
  private double sellPrice; // 卖出价
  private Date addTime; // 时间

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

  public double getBuyPrice() {
    return buyPrice;
  }

  public void setBuyPrice(double buyPrice) {
    this.buyPrice = buyPrice;
  }

  public double getSellPrice() {
    return sellPrice;
  }

  public void setSellPrice(double sellPrice) {
    this.sellPrice = sellPrice;
  }

  public Date getAddTime() {
    return addTime;
  }

  public void setAddTime(Date addTime) {
    this.addTime = addTime;
  }
}
