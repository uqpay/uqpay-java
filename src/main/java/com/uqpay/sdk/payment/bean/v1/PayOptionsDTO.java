package com.uqpay.sdk.payment.bean.v1;

import com.uqpay.sdk.utils.Constants;
import com.uqpay.sdk.utils.enums.ClientType;
import com.uqpay.sdk.utils.enums.UqpayScanType;
import com.uqpay.sdk.utils.enums.UqpayTransType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import java.util.Map;

public class PayOptionsDTO implements PaygateParams {
  /**
   * is required
   */
  @ParamLink(Constants.PAY_OPTIONS_METHOD_ID)
  @PositiveOrZero
  private int methodId;
  @ParamLink(Constants.PAY_OPTIONS_CLIENT_TYPE)
  @NotNull
  private ClientType client; // only required for in app payment
  @ParamLink(Constants.PAY_OPTIONS_TRADE_TYPE)
  @NotNull
  private UqpayTransType tradeType;

  /**
   * not required for each payment API
   */
  @ParamLink(Constants.PAY_OPTIONS_ASYNC_NOTICE_URL)
  private String callbackUrl; // async notice url
  @ParamLink(Constants.PAY_OPTIONS_SYNC_NOTICE_URL)
  private String returnUrl; // sync notice url
  @ParamLink(Constants.PAY_OPTIONS_SCAN_TYPE)
  private UqpayScanType scanType; // only required for qr code payment
  @ParamLink(Constants.PAY_OPTIONS_IDENTITY)
  private String identity; // only required for qr code payment when scan type is Merchant or the pay method is offline qr code
  @ParamLink(Constants.BANK_CARD_CARD_NUM)
  private String cardNum; // optional and only use for UnionSecurePay

  @ParamLink(Constants.PAY_OPTIONS_MERCHANT_CITY)
  private String merchantCity; //城市
  @ParamLink(Constants.PAY_OPTIONS_TERMINAL_ID)
  private String terminalID; //终端号

  @ParamLink(value = Constants.ORDER_CHANNEL_INFO, targetType = "JSON")
  private Map<String,String> channelInfo;
  @ParamLink(value = Constants.ORDER_EXTEND_INFO, targetType = "JSON")
  private Map<String,String> extendInfo;

  /**
   * only use for 3D CreditCard Payment,
   * This URL is used to get the paResponse sent by the bank
   * the paResponse will be used to finished the 3D payment, see {@link ThreeDFinishDTO}
   */
  private String paResCbUrl;

  public UqpayTransType getTradeType() {
    return tradeType;
  }

  public void setTradeType(UqpayTransType tradeType) {
    this.tradeType = tradeType;
  }

  public int getMethodId() {
    return methodId;
  }

  public void setMethodId(int methodId) {
    this.methodId = methodId;
  }

  public String getCallbackUrl() {
    return callbackUrl;
  }

  public void setCallbackUrl(String callbackUrl) {
    this.callbackUrl = callbackUrl;
  }

  public ClientType getClient() {
    return client;
  }

  public void setClient(ClientType client) {
    this.client = client;
  }

  public String getReturnUrl() {
    return returnUrl;
  }

  public void setReturnUrl(String returnUrl) {
    this.returnUrl = returnUrl;
  }

  public UqpayScanType getScanType() {
    return scanType;
  }

  public void setScanType(UqpayScanType scanType) {
    this.scanType = scanType;
  }

  public String getIdentity() {
    return identity;
  }

  public void setIdentity(String identity) {
    this.identity = identity;
  }

  public Map<String, String> getChannelInfo() {
    return channelInfo;
  }

  public void setChannelInfo(Map<String, String> channelInfo) {
    this.channelInfo = channelInfo;
  }

  public Map<String, String> getExtendInfo() {
    return extendInfo;
  }

  public void setExtendInfo(Map<String, String> extendInfo) {
    this.extendInfo = extendInfo;
  }

  public String getMerchantCity() {
    return merchantCity;
  }

  public void setMerchantCity(String merchantCity) {
    this.merchantCity = merchantCity;
  }

  public String getTerminalID() {
    return terminalID;
  }

  public void setTerminalID(String terminalID) {
    this.terminalID = terminalID;
  }

  public String getPaResCbUrl() {
    return paResCbUrl;
  }

  public void setPaResCbUrl(String paResCbUrl) {
    this.paResCbUrl = paResCbUrl;
  }

  public String getCardNum() {
    return cardNum;
  }

  public void setCardNum(String cardNum) {
    this.cardNum = cardNum;
  }
}
