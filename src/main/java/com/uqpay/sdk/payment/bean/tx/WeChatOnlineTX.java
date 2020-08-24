package com.uqpay.sdk.payment.bean.tx;

public class WeChatOnlineTX extends BasicTX {
  private static final long serialVersionUID = 1399220547463637471L;
  // Specifies an Official Account ID assigned by WeChat
  private String appId;
  // The only user identification under the current appid. About how to get openid, please refer to get openid api. see https://developers.weixin.qq.com/doc/offiaccount/en/OA_Web_Apps/Wechat_webpage_authorization.html
  private String openId;

  public String getOpenId() {
    return openId;
  }

  public void setOpenId(String openId) {
    this.openId = openId;
  }

  public String getAppId() {
    return appId;
  }

  public void setAppId(String appId) {
    this.appId = appId;
  }
}
