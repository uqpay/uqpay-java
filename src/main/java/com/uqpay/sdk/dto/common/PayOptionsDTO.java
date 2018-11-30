package com.uqpay.sdk.dto.common;

import com.uqpay.sdk.dto.ParamLink;
import com.uqpay.sdk.dto.PayOptions;
import com.uqpay.sdk.dto.PaygateParams;
import com.uqpay.sdk.dto.pay.PayOrder;
import com.uqpay.sdk.dto.preAuth.PreAuthOrder;
import com.uqpay.sdk.utils.Constants;
import com.uqpay.sdk.utils.PaymentSupportClient;
import com.uqpay.sdk.utils.UqpayScanType;
import com.uqpay.sdk.utils.UqpayTradeType;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Map;

public class PayOptionsDTO implements PaygateParams, PayOptions {
  /**
   * is required
   */
  @ParamLink(Constants.PAY_OPTIONS_METHOD_ID)
  @Positive
  private int methodId;
  @ParamLink(Constants.PAY_OPTIONS_ASYNC_NOTICE_URL)
  @NotEmpty
  private String callbackUrl; // async notice url
  @ParamLink(Constants.PAY_OPTIONS_CLIENT_TYPE)
  @NotNull
  private PaymentSupportClient client; // only required for in app payment
  @ParamLink(Constants.PAY_OPTIONS_TRADE_TYPE)
  @NotNull
  private UqpayTradeType tradeType;

  /**
   * not required for each payment API
   */
  @ParamLink(Constants.PAY_OPTIONS_SYNC_NOTICE_URL)
  private String returnUrl; // sync notice url
  @ParamLink(Constants.PAY_OPTIONS_SCAN_TYPE)
  private UqpayScanType scanType; // only required for qr code payment
  @ParamLink(Constants.PAY_OPTIONS_IDENTITY)
  private String identity; // only required for qr code payment when scan type is Merchant or the pay method is offline qr code

  @ParamLink(Constants.PAY_OPTIONS_MERCHANT_CITY)
  private String merchantCity; //城市
  @ParamLink(Constants.PAY_OPTIONS_TERMINAL_ID)
  private String terminalID; //终端号

  @ParamLink(value = Constants.ORDER_EXTEND_INFO, targetType = "JSON")
  private Map<String,String> channelInfo;
  @ParamLink(value = Constants.ORDER_CHANNEL_INFO, targetType = "JSON")
  private Map<String,String> extendInfo;

  public static PayOptionsDTO valueOf(PayOrder order) {
    PayOptionsDTO result = new PayOptionsDTO();
    BeanUtils.copyProperties(order, result);
    return result;
  }

  public static PayOptionsDTO valueOf(PreAuthOrder order) {
    PayOptionsDTO result = new PayOptionsDTO();
    BeanUtils.copyProperties(order, result);
    return result;
  }

  public UqpayTradeType getTradeType() {
    return tradeType;
  }

  public void setTradeType(UqpayTradeType tradeType) {
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

  public PaymentSupportClient getClient() {
    return client;
  }

  public void setClient(PaymentSupportClient client) {
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
}
