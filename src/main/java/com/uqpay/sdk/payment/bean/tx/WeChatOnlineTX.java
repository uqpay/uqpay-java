package com.uqpay.sdk.payment.bean.tx;

public class WeChatOnlineTX extends BasicTX {
  private static final long serialVersionUID = -8963667848090873328L;
  private String openId;

  public String getOpenId() {
    return openId;
  }

  public void setOpenId(String openId) {
    this.openId = openId;
  }
}
