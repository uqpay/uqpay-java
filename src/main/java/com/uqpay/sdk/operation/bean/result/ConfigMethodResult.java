package com.uqpay.sdk.operation.bean.result;

public class ConfigMethodResult extends BaseAppgateResult {
  private static final long serialVersionUID = -5053269423824653145L;
  private boolean isSuccess;
  private int methodId;

  public boolean isSuccess() {
    return isSuccess;
  }

  public void setSuccess(boolean success) {
    isSuccess = success;
  }

  public int getMethodId() {
    return methodId;
  }

  public void setMethodId(int methodId) {
    this.methodId = methodId;
  }
}
