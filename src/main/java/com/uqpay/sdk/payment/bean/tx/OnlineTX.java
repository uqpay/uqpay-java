package com.uqpay.sdk.payment.bean.tx;

public class OnlineTX extends BasicTX {
  private static final long serialVersionUID = -5965427775229074205L;
  private String returnUrl;
  private String cardNum;

  public String getReturnUrl() {
    return returnUrl;
  }

  public void setReturnUrl(String returnUrl) {
    this.returnUrl = returnUrl;
  }

  public String getCardNum() {
    return cardNum;
  }

  public void setCardNum(String cardNum) {
    this.cardNum = cardNum;
  }
}
