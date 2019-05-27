package com.uqpay.sdk.dto.result.appgate;

import java.util.List;

public class BalanceResult extends BaseAppgateResult {
  private static final long serialVersionUID = -2261909361310525935L;
  private List<Balance> balanceList;

  public List<Balance> getBalanceList() {
    return balanceList;
  }

  public void setBalanceList(List<Balance> balanceList) {
    this.balanceList = balanceList;
  }
}
