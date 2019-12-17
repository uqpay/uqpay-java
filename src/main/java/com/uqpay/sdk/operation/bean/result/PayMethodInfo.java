package com.uqpay.sdk.operation.bean.result;

import java.util.List;

public class PayMethodInfo {
  private Integer methodId;
  private boolean enabled;
  private List<String> tradeCurrency;

  public Integer getMethodId() {
    return methodId;
  }

  public void setMethodId(Integer methodId) {
    this.methodId = methodId;
  }

  public boolean isEnabled() {
    return enabled;
  }

  public void setEnabled(boolean enabled) {
    this.enabled = enabled;
  }

  public List<String> getTradeCurrency() {
    return tradeCurrency;
  }

  public void setTradeCurrency(List<String> tradeCurrency) {
    this.tradeCurrency = tradeCurrency;
  }
}
