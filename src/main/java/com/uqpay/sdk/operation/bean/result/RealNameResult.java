package com.uqpay.sdk.operation.bean.result;

public class RealNameResult extends BaseAppgateResult {

  private static final long serialVersionUID = 2197289889879231669L;
  private String panHash;
  private boolean result;

  public String getPanHash() {
    return panHash;
  }

  public void setPanHash(String panHash) {
    this.panHash = panHash;
  }

  public boolean isResult() {
    return result;
  }

  public void setResult(boolean result) {
    this.result = result;
  }
}
