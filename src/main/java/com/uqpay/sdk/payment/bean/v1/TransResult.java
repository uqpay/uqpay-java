package com.uqpay.sdk.payment.bean.v1;

import com.uqpay.sdk.utils.Constants;
import com.uqpay.sdk.utils.enums.UqpayScanType;

import java.util.Map;

public class TransResult extends BaseResult {
  private static final long serialVersionUID = 2394757051743388283L;
  @ParamLink(Constants.ORDER_ID)
  private String orderId;
  @ParamLink(Constants.RESULT_UQPAY_ORDER_ID)
  private long uqOrderId; // this order id generate by uqpay
  @ParamLink(Constants.ORDER_AMOUNT)
  private double amount;
  @ParamLink(value = Constants.ORDER_CURRENCY)
  private String currency;
  @ParamLink(value = Constants.RESULT_STATE)
  private String state; // order state
  @ParamLink(value = Constants.ORDER_CHANNEL_INFO, targetType = "JSON")
  private Map<String,String> channelInfo;
  @ParamLink(value = Constants.ORDER_EXTEND_INFO, targetType = "JSON")
  private Map<String,String> extendInfo;

  /**
   * this result valued when IN-APP payment
   */
  @ParamLink(Constants.RESULT_ACCEPT_CODE)
  private String acceptCode; // this code will be used by UQPAY Mobile Client SDK to generate the wallet app url scheme

  /**
   * these results valued when the payment want return some channel info
   */
  @ParamLink(Constants.RESULT_CHANNEL_CODE)
  private String channelCode;
  @ParamLink(Constants.RESULT_CHANNEL_MESSAGE)
  private String channelMsg;

  /**
   * these results valued when QRCode payment
   */
  @ParamLink(Constants.PAY_OPTIONS_SCAN_TYPE)
  private UqpayScanType scanType;
  @ParamLink(Constants.RESULT_QR_CODE_URL)
  private String qrCodeUrl;
  @ParamLink(Constants.RESULT_QR_CODE_DATA)
  private String qrCode;

  /**
   * other merchant info
   */
  @ParamLink(Constants.ORDER_STORE_ID)
  private String storeId; // if you request with this info, the UQPAY will return it to your

  @ParamLink(Constants.ORDER_SELLER)
  private String seller; // if you request with this info, the UQPAY will return it to your

  /**
   * this result only valued when ThreeD CreditCard and Online Payment
   * if this result is valued, the others will be null
   * user can return this data to client, and post them with media type "application/x-www-form-urlencoded" to the apiURL (which u can get from this data)
   */
  private RedirectPostData redirectPostData;

  public TransResult() {}

  public TransResult(Map<String, String> postData, String apiURL) {
    RedirectPostData redirectPost = new RedirectPostData();
    redirectPost.setApiURL(apiURL);
    redirectPost.setPostData(postData);
    this.redirectPostData = redirectPost;
  }

  public String getOrderId() {
    return orderId;
  }

  public void setOrderId(String orderId) {
    this.orderId = orderId;
  }

  public long getUqOrderId() {
    return uqOrderId;
  }

  public void setUqOrderId(long uqOrderId) {
    this.uqOrderId = uqOrderId;
  }

  public double getAmount() {
    return amount;
  }

  public void setAmount(double amount) {
    this.amount = amount;
  }

  public String getCurrency() {
    return currency;
  }

  public void setCurrency(String currency) {
    this.currency = currency;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
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

  public String getAcceptCode() {
    return acceptCode;
  }

  public void setAcceptCode(String acceptCode) {
    this.acceptCode = acceptCode;
  }

  public String getChannelCode() {
    return channelCode;
  }

  public void setChannelCode(String channelCode) {
    this.channelCode = channelCode;
  }

  public String getChannelMsg() {
    return channelMsg;
  }

  public void setChannelMsg(String channelMsg) {
    this.channelMsg = channelMsg;
  }

  public UqpayScanType getScanType() {
    return scanType;
  }

  public void setScanType(UqpayScanType scanType) {
    this.scanType = scanType;
  }

  public String getQrCodeUrl() {
    return qrCodeUrl;
  }

  public void setQrCodeUrl(String qrCodeUrl) {
    this.qrCodeUrl = qrCodeUrl;
  }

  public String getQrCode() {
    return qrCode;
  }

  public void setQrCode(String qrCode) {
    this.qrCode = qrCode;
  }

  public String getStoreId() {
    return storeId;
  }

  public void setStoreId(String storeId) {
    this.storeId = storeId;
  }

  public String getSeller() {
    return seller;
  }

  public void setSeller(String seller) {
    this.seller = seller;
  }

  public RedirectPostData getRedirectPostData() {
    return redirectPostData;
  }

  public void setRedirectPostData(RedirectPostData redirectPostData) {
    this.redirectPostData = redirectPostData;
  }

}
