package com.uqpay.sdk.utils;

public enum PayMethodEnum {
  UnionPayQR(PayMethod.UnionPayQR, ScenesEnum.QRCode),
  AlipayQR(PayMethod.AlipayQR, ScenesEnum.QRCode),
  WeChatQR(PayMethod.WeChatQR, ScenesEnum.QRCode),
  WeChat_WebBased_InApp(PayMethod.Wechat_WebBased_InApp, ScenesEnum.RedirectPay),
  UnionPayOnline(PayMethod.UnionPayOnline, ScenesEnum.RedirectPay),
  VISA(PayMethod.VISA, ScenesEnum.CreditCard),
  VISA3D(PayMethod.VISA3D, ScenesEnum.ThreeDCreditCard),
  Master(PayMethod.Master, ScenesEnum.CreditCard),
  Master3D(PayMethod.Master3D, ScenesEnum.ThreeDCreditCard),
  UnionPay(PayMethod.UnionPay, ScenesEnum.CreditCard),
  AMEX(PayMethod.AMEX, ScenesEnum.CreditCard),
  JCB(PayMethod.JCB, ScenesEnum.CreditCard),
  PayPal(PayMethod.PayPal, ScenesEnum.CreditCard),
  Alipay(PayMethod.Alipay, ScenesEnum.RedirectPay),
  AlipayWap(PayMethod.AlipayWap, ScenesEnum.RedirectPay),
  Wechat_InAPP(PayMethod.Wechat_InAPP, ScenesEnum.InApp),
  UnionPay_InAPP(PayMethod.UnionPay_InAPP, ScenesEnum.InApp),
  UnionPay_Online_InAPP(PayMethod.UnionPay_Online_InAPP, ScenesEnum.InApp),
  ApplePay(PayMethod.ApplePay, ScenesEnum.RedirectPay),
  UnionPay_Merchant_Host(PayMethod.Union_Merchant_Host, ScenesEnum.MerchantHost);

  private int value;
  private ScenesEnum scenes;

  PayMethodEnum(int value, ScenesEnum scenes) {
    this.value = value;
    this.scenes = scenes;
  }

  public static PayMethodEnum valueOf(int value) {
    for (PayMethodEnum val : PayMethodEnum.values()) {
      if (value == val.getValue()) {
        return val;
      }
    }
    return null;
  }

  public int getValue() {
    return value;
  }

  public ScenesEnum getScenes() {
    return scenes;
  }
}
