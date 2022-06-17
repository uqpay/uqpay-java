package com.uqpay.sdk.utils.enums;

import com.uqpay.sdk.utils.PayMethod;

/**
 * <p>PayMethodEnum class.</p>
 *
 * @author zhengwei
 * @version $Id: $Id
 */
public enum PayMethodEnum {
  UnionPayOnlineQR(PayMethod.UnionPayOnlineQR, ScenesEnum.OnlineQRCode),
  UnionPayOfflineQR(PayMethod.UnionPayOfflineQR, ScenesEnum.OfflineQRCode),
  AlipayOfflineQR(PayMethod.AlipayOfflineQR, ScenesEnum.OfflineQRCode),
  WeChatOfflineQR(PayMethod.WeChatOfflineQR, ScenesEnum.OfflineQRCode),
  GradOfflineQR(PayMethod.GradOfflineQR, ScenesEnum.OfflineQRCode),
  GradOnlineQR(PayMethod.GradOnlineQR, ScenesEnum.OnlineQRCode),
  AlipayOnlineQR(PayMethod.AlipayOnlineQR, ScenesEnum.OnlineQRCode),
  WeChatOnlineQR(PayMethod.WeChatOnlineQR, ScenesEnum.OnlineQRCode),
  DBS_OnlineQR(PayMethod.DBS_OnlineQR, ScenesEnum.OnlineQRCode),
  DBS_OfflineQR(PayMethod.DBS_OfflineQR, ScenesEnum.OfflineQRCode),
  ShopeeOfflineQR(PayMethod.ShopeeOfflineQR, ScenesEnum.OfflineQRCode),
  XNAP(PayMethod.XNAP, ScenesEnum.OfflineQRCode),
  ThaiQR(PayMethod.ThaiQR, ScenesEnum.OfflineQRCode),
  TNGOfflineQR(PayMethod.TNGOfflineQR, ScenesEnum.OfflineQRCode),
  KAKAOPAYOfflineQR(PayMethod.KAKAOPAYOfflineQR, ScenesEnum.OfflineQRCode),
  PayLahOfflineQR(PayMethod.PayLahOfflineQR, ScenesEnum.OfflineQRCode),
  ShopeeOnlineQR(PayMethod.ShopeeOnlineQR, ScenesEnum.OnlineQRCode),
  RazerPay_OfflineQR(PayMethod.RazerPay_OfflineQR, ScenesEnum.OfflineQRCode),
  DASH_OfflineOR(PayMethod.DASH_OfflineOR, ScenesEnum.OfflineQRCode),
  DASH_OnlineOR(PayMethod.DASH_OnlineOR, ScenesEnum.OnlineQRCode),
  DIGICCY(PayMethod.DIGICCY, ScenesEnum.OnlineQRCode),
  WechatOfficialAccount(PayMethod.WechatOfficialAccount, ScenesEnum.DirectPay),
  WechatMiniProgram(PayMethod.WechatMiniProgram, ScenesEnum.DirectPay),
  UnionSecurePay(PayMethod.UnionSecurePay, ScenesEnum.OnlinePay),
  GatewayPayment(PayMethod.GatewayPayment, ScenesEnum.OnlinePay),
  ShopeeOnline(PayMethod.ShopeeOnline, ScenesEnum.OnlinePay),
  DANAOnline(PayMethod.DANAOnline, ScenesEnum.OnlinePay),
  GCASHOnline(PayMethod.GCASHOnline, ScenesEnum.OnlinePay),
  KAKAOPAYOnline(PayMethod.KAKAOPAYOnline, ScenesEnum.OnlinePay),
  TNGOnline(PayMethod.TNGOnline, ScenesEnum.OnlinePay),
  TrueMoneyOnline(PayMethod.TrueMoneyOnline, ScenesEnum.OnlinePay),
  AlipayHKOnline(PayMethod.AlipayHKOnline, ScenesEnum.OnlinePay),
  GrabOTC(PayMethod.GrabOTC, ScenesEnum.OnlinePay),
  VISA(PayMethod.VISA, ScenesEnum.CreditCard),
  VISA3D(PayMethod.VISA3D, ScenesEnum.ThreeDCreditCard),
  Master(PayMethod.Master, ScenesEnum.CreditCard),
  Master3D(PayMethod.Master3D, ScenesEnum.ThreeDCreditCard),
  UnionPayExpressPay(PayMethod.UnionPayExpressPay, ScenesEnum.CreditCard),
  AMEX(PayMethod.AMEX, ScenesEnum.CreditCard),
  JCB(PayMethod.JCB, ScenesEnum.CreditCard),
  PayPal(PayMethod.PayPal, ScenesEnum.CreditCard),
  AlipayWebOnline(PayMethod.AlipayWebOnline, ScenesEnum.OnlinePay),
  FreeCharge(PayMethod.FreeCharge, ScenesEnum.OnlinePay),
  AlipayWap(PayMethod.AlipayWap, ScenesEnum.OnlinePay),
  Wechat_InAPP(PayMethod.Wechat_InAPP, ScenesEnum.InApp),
  UnionPay_InAPP(PayMethod.UnionPay_InAPP, ScenesEnum.InApp),
  Alipay_InAPP(PayMethod.Alipay_InAPP, ScenesEnum.InApp),
  Shopee_InAPP(PayMethod.Shopee_InAPP, ScenesEnum.InApp),
  ApplePay(PayMethod.ApplePay, ScenesEnum.OnlinePay),
  UnionPayMerchantHost(PayMethod.UnionPayMerchantHost, ScenesEnum.MerchantHost),
  UnionPayServerHost(PayMethod.UnionPayServerHost, ScenesEnum.ServerHost),
  BTC_Online(PayMethod.BTC_Online, ScenesEnum.OnlineQRCode),
  BTC_Offline(PayMethod.BTC_Offline, ScenesEnum.OfflineQRCode),
  BCH_Online(PayMethod.BCH_Online, ScenesEnum.OnlineQRCode),
  BCH_Offline(PayMethod.BCH_Offline, ScenesEnum.OfflineQRCode),
  ETH_Online(PayMethod.ETH_Online, ScenesEnum.OnlineQRCode),
  ETH_Offline(PayMethod.ETH_Offline, ScenesEnum.OfflineQRCode),
  USDT_Online(PayMethod.USDT_Online, ScenesEnum.OnlineQRCode),
  USDT_Offline(PayMethod.USDT_Offline, ScenesEnum.OfflineQRCode),
  USDC_Online(PayMethod.USDC_Online, ScenesEnum.OnlineQRCode),
  USDC_Offline(PayMethod.USDC_Offline, ScenesEnum.OfflineQRCode),
  REAL_NAME_PAYMENT(PayMethod.REAL_NAME_PAYMENT, ScenesEnum.OnlinePay);

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
