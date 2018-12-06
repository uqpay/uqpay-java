package com.uqpay.sdk.utils.enums;

import com.uqpay.sdk.utils.PayMethod;

/**
 * <p>PayMethodEnum class.</p>
 *
 * @author zhengwei
 * @version $Id: $Id
 */
public enum PayMethodEnum {
  UnionPayQR(PayMethod.UnionPayQR, ScenesEnum.QRCode),
  UnionPayOfflineQR(PayMethod.UnionPayOfflineQR, ScenesEnum.OfflineQRCode),
  AlipayQR(PayMethod.AlipayQR, ScenesEnum.QRCode),
  WeChatQR(PayMethod.WeChatQR, ScenesEnum.QRCode),
  WechatWebBasedInApp(PayMethod.WechatWebBasedInApp, ScenesEnum.RedirectPay),
  UnionSecurePay(PayMethod.UnionSecurePay, ScenesEnum.RedirectPay),
  VISA(PayMethod.VISA, ScenesEnum.CreditCard),
  VISA3D(PayMethod.VISA3D, ScenesEnum.ThreeDCreditCard),
  Master(PayMethod.Master, ScenesEnum.CreditCard),
  Master3D(PayMethod.Master3D, ScenesEnum.ThreeDCreditCard),
  UnionPayExpressPay(PayMethod.UnionPayExpressPay, ScenesEnum.CreditCard),
  AMEX(PayMethod.AMEX, ScenesEnum.CreditCard),
  JCB(PayMethod.JCB, ScenesEnum.CreditCard),
  PayPal(PayMethod.PayPal, ScenesEnum.CreditCard),
  Alipay(PayMethod.Alipay, ScenesEnum.RedirectPay),
  AlipayWap(PayMethod.AlipayWap, ScenesEnum.RedirectPay),
  Wechat_InAPP(PayMethod.Wechat_InAPP, ScenesEnum.InApp),
  UnionPay_InAPP(PayMethod.UnionPay_InAPP, ScenesEnum.InApp),
  ApplePay(PayMethod.ApplePay, ScenesEnum.RedirectPay),
  UnionPayMerchantHost(PayMethod.UnionPayMerchantHost, ScenesEnum.MerchantHost),
  UnionPayServerHost(PayMethod.UnionPayServerHost, ScenesEnum.ServerHost);

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
   * @return a {@link PayMethodEnum} object.
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
   * @return a {@link ScenesEnum} object.
   */
  public ScenesEnum getScenes() {
    return scenes;
  }
}
