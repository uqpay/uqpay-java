package com.uqpay.sdk.utils;

public enum ScenesEnum {
  Global, //全局
  Unknown, //未知
  QRCode,//扫码
  CreditCard,//信用卡支付
  ThreeDCreditCard,//3DSecure卡支付
  OnlinePay,//在线支付（跳转）
  DirectPay,//在线支付（直接返回结果）
  AuthPay,//存在验证环节的支付，如银联鉴权支付
  EmbedPay,//嵌入方式支付
  CNOnlinePay,//国内网银支付
  InApp
}
