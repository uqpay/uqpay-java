package com.uqpay.sdk.payment.bean.tx;

public class AuthQuickTX extends BasicTX{

  private String realName;

  @Encrypt
  private String cardNum;

  @Encrypt
  private String idNo;

  @Encrypt
  private String phone;

  public String getRealName() {
    return realName;
  }

  public void setRealName(String realName) {
    this.realName = realName;
  }

  public String getCardNum() {
    return cardNum;
  }

  public void setCardNum(String cardNum) {
    this.cardNum = cardNum;
  }

  public String getIdNo() {
    return idNo;
  }

  public void setIdNo(String idNo) {
    this.idNo = idNo;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }
}
