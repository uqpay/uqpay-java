package com.uqpay.sdk.operation.bean.result;

public class Balance {
  private double balance;
  private double freezeBalance;
  private double unsettledBalance;

  private String currency;

  public double getBalance() {
    return balance;
  }

  public void setBalance(double balance) {
    this.balance = balance;
  }

  public double getFreezeBalance() {
    return freezeBalance;
  }

  public void setFreezeBalance(double freezeBalance) {
    this.freezeBalance = freezeBalance;
  }

  public double getUnsettledBalance() {
    return unsettledBalance;
  }

  public void setUnsettledBalance(double unsettledBalance) {
    this.unsettledBalance = unsettledBalance;
  }

  public String getCurrency() {
    return currency;
  }

  public void setCurrency(String currency) {
    this.currency = currency;
  }
}
