package com.uqpay.sdk.utils;

/**
 * <p>PayMethodEnum class.</p>
 *
 * @author zhengwei
 * @version $Id: $Id
 */
public enum PayMethodEnum {
  UnionPayQR(1001, ScenesEnum.QRCode),
  AlipayQR(1002, ScenesEnum.QRCode),
  WeChatQR(1003, ScenesEnum.QRCode),
  WeChatH5(1102, ScenesEnum.OnlinePay),
  UnionPayOnline(1100, ScenesEnum.OnlinePay),
  VISA(1200, ScenesEnum.CreditCard),
  VISA3D(1250, ScenesEnum.ThreeDCreditCard),
  Master(1201, ScenesEnum.CreditCard),
  Master3D(1251, ScenesEnum.ThreeDCreditCard),
  UnionPay(1202, ScenesEnum.CreditCard),
  AMEX(1203, ScenesEnum.CreditCard),
  JCB(1204, ScenesEnum.CreditCard),
  PayPal(1300, ScenesEnum.CreditCard),
  Alipay(1301, ScenesEnum.OnlinePay),
  AlipayWap(1501, ScenesEnum.OnlinePay),
  Wechat_InAPP(2000, ScenesEnum.InApp),
  UnionPay_InAPP(2001, ScenesEnum.InApp),
  ApplePay(3000, ScenesEnum.OnlinePay);

  private int value;
  private ScenesEnum scenes;

  PayMethodEnum(int value, ScenesEnum scenes) {
    this.value = value;
    this.scenes = scenes;
  }

  /**
   * <p>valueOf.</p>
   *
   * @param value a int.
   * @return a {@link com.uqpay.sdk.utils.PayMethodEnum} object.
   */
  public static PayMethodEnum valueOf(int value) {
    for (PayMethodEnum val : PayMethodEnum.values()) {
      if (value == val.getValue()) {
        return val;
      }
    }
    return null;
  }

  /**
   * <p>Getter for the field <code>value</code>.</p>
   *
   * @return a int.
   */
  public int getValue() {
    return value;
  }

  /**
   * <p>Getter for the field <code>scenes</code>.</p>
   *
   * @return a {@link com.uqpay.sdk.utils.ScenesEnum} object.
   */
  public ScenesEnum getScenes() {
    return scenes;
  }
}
