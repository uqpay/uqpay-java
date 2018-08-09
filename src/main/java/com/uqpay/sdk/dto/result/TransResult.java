package com.uqpay.sdk.dto.result;

import com.uqpay.sdk.utils.Constants;
import com.uqpay.sdk.utils.UqpayTradeType;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

public class TransResult implements Serializable {
  private static final long serialVersionUID = -4038448421853606000L;
  protected String sign;
  protected int merchantId;
  protected UqpayTradeType tradeType;
  protected String code;
  protected String message;
  protected Date date;
  protected String orderId;
  protected long uqOrderId;
  protected int methodId;
  protected double amount;
  protected String currency;
  protected String state;
  protected String channelInfo;
  protected String extendInfo;

  public TransResult() {}

  public TransResult(Map<String, Object> mapResult) {
    this.code = (String) mapResult.get(Constants.RESULT_CODE);
    this.sign = (String) mapResult.get(Constants.AUTH_SIGN);
    this.merchantId = Integer.valueOf(mapResult.get(Constants.AUTH_MERCHANT_ID).toString());
    this.tradeType = UqpayTradeType.valueOf((String) mapResult.get(Constants.PAY_OPTIONS_TRADE_TYPE));
    this.message = (String) mapResult.get(Constants.RESULT_MESSAGE);
    this.date = new Date(Long.valueOf(mapResult.get(Constants.ORDER_DATE).toString()));
    this.orderId = (String) mapResult.get(Constants.ORDER_ID);
    this.uqOrderId = Long.valueOf(mapResult.get(Constants.RESULT_UQPAY_ORDER_ID).toString());
    this.methodId = Integer.valueOf(mapResult.get(Constants.PAY_OPTIONS_METHOD_ID).toString());
    this.amount = Double.valueOf(mapResult.get(Constants.ORDER_AMOUNT).toString());
    this.currency = (String) mapResult.get(Constants.ORDER_CURRENCY);
    this.state = (String) mapResult.get(Constants.RESULT_STATE);
    this.channelInfo = (String) mapResult.get(Constants.ORDER_CHANNEL_INFO);
    this.extendInfo = (String) mapResult.get(Constants.ORDER_EXTEND_INFO);
  }

  public String getSign() {
    return sign;
  }

  public void setSign(String sign) {
    this.sign = sign;
  }

  public int getMerchantId() {
    return merchantId;
  }

  public void setMerchantId(int merchantId) {
    this.merchantId = merchantId;
  }

  public UqpayTradeType getTradeType() {
    return tradeType;
  }

  public void setTradeType(UqpayTradeType tradeType) {
    this.tradeType = tradeType;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
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

  public int getMethodId() {
    return methodId;
  }

  public void setMethodId(int methodId) {
    this.methodId = methodId;
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

  public String getChannelInfo() {
    return channelInfo;
  }

  public void setChannelInfo(String channelInfo) {
    this.channelInfo = channelInfo;
  }

  public String getExtendInfo() {
    return extendInfo;
  }

  public void setExtendInfo(String extendInfo) {
    this.extendInfo = extendInfo;
  }
}
