package com.uqpay.sdk.payment.bean.v1;

import com.uqpay.sdk.utils.Constants;
import com.uqpay.sdk.utils.enums.UqpayScanType;

import java.util.Currency;
import java.util.Date;
import java.util.Map;

public class QueryResult extends BaseResult {
  private static final long serialVersionUID = -7671944552467915032L;

  @ParamLink(Constants.ORDER_ID)
  private String orderId;
  @ParamLink(Constants.RESULT_UQPAY_ORDER_ID)
  private long uqOrderId;
  @ParamLink(Constants.RESULT_UQPAY_RELATED_ID)
  private String relatedId; // related order id (uqpay order), if this is a refund/cancel/withdraw order
  @ParamLink(Constants.RESULT_STATE)
  private String state;
  @ParamLink(Constants.ORDER_AMOUNT)
  private double amount;
  @ParamLink(value = Constants.ORDER_CURRENCY)
  private Currency currency;
  @ParamLink(Constants.ORDER_FinishTime)
  private Date finishTime;
  @ParamLink(Constants.PAY_OPTIONS_SCAN_TYPE)
  private UqpayScanType scanType;
  @ParamLink(value = Constants.ORDER_EXTEND_INFO, targetType = "JSON")
  private Map<String,String> channelInfo;
  @ParamLink(value = Constants.ORDER_CHANNEL_INFO, targetType = "JSON")
  private Map<String,String> extendInfo;

  public QueryResult() {
  }

  public double getAmount() {
    return amount;
  }

  public void setAmount(double amount) {
    this.amount = amount;
  }

  public Currency getCurrency() {
    return currency;
  }

  public void setCurrency(Currency currency) {
    this.currency = currency;
  }

  public Date getFinishTime() {
    return finishTime;
  }

  public void setFinishTime(Date finishTime) {
    this.finishTime = finishTime;
  }

  public UqpayScanType getScanType() {
    return scanType;
  }

  public void setScanType(UqpayScanType scanType) {
    this.scanType = scanType;
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

  public String getRelatedId() {
    return relatedId;
  }

  public void setRelatedId(String relatedId) {
    this.relatedId = relatedId;
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
}
