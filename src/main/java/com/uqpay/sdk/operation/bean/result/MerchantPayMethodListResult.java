package com.uqpay.sdk.operation.bean.result;

import java.util.List;

public class MerchantPayMethodListResult extends BaseAppgateResult {
  private static final long serialVersionUID = 3061937634713060407L;
  private List<PayMethodInfo> methodList;

  public List<PayMethodInfo> getMethodList() {
    return methodList;
  }

  public void setMethodList(List<PayMethodInfo> methodList) {
    this.methodList = methodList;
  }
}
