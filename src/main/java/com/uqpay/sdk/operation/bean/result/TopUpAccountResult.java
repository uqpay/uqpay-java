package com.uqpay.sdk.operation.bean.result;

public class TopUpAccountResult extends BaseAppgateResult {

  private String accountName;
  private String account;
  private String swift;
  private String bankName;
  private String bankAddress;
  private String address;

  public String getAccountName() {
    return accountName;
  }

  public void setAccountName(String accountName) {
    this.accountName = accountName;
  }

  public String getAccount() {
    return account;
  }

  public void setAccount(String account) {
    this.account = account;
  }

  public String getSwift() {
    return swift;
  }

  public void setSwift(String swift) {
    this.swift = swift;
  }

  public String getBankName() {
    return bankName;
  }

  public void setBankName(String bankName) {
    this.bankName = bankName;
  }

  public String getBankAddress() {
    return bankAddress;
  }

  public void setBankAddress(String bankAddress) {
    this.bankAddress = bankAddress;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }
}
