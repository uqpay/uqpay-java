package com.uqpay.sdk.dto.result;

import com.uqpay.sdk.utils.Constants;
import com.uqpay.sdk.utils.UqpayTradeType;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

public class QueryResult implements Serializable {
  protected String sign;
  protected int merchantId;
  protected UqpayTradeType tradeType;
  protected String code;
  protected String message;
  protected Date date;
  protected String orderId;
  protected long uqOrderId;
  protected String relatedId; // 关联的订单ID，当发生退款、撤销时，对应订单
  protected int methodId;
  protected String state;
  protected String channelInfo;
  protected String extendInfo;

  public QueryResult() {
  }

  public QueryResult(Map<String, Object> mapResult) {
    this.code = mapResult.get(Constants.RESULT_CODE).toString();
    this.sign = mapResult.get(Constants.AUTH_SIGN).toString();
    this.merchantId = Integer.valueOf(mapResult.get(Constants.AUTH_MERCHANT_ID).toString());
    this.tradeType = UqpayTradeType.valueOf((String) mapResult.get(Constants.PAY_OPTIONS_TRADE_TYPE));
    this.message = mapResult.get(Constants.RESULT_MESSAGE).toString();
    this.date = new Date(Long.valueOf(mapResult.get(Constants.ORDER_DATE).toString()));
    this.orderId = mapResult.get(Constants.ORDER_ID).toString();
    this.uqOrderId = Long.valueOf(mapResult.get(Constants.RESULT_UQPAY_ORDER_ID).toString());
    this.methodId = Integer.valueOf(mapResult.get(Constants.PAY_OPTIONS_METHOD_ID).toString());
    this.state = mapResult.get(Constants.RESULT_STATE).toString();
    this.channelInfo = mapResult.get(Constants.ORDER_CHANNEL_INFO).toString();
    this.extendInfo = mapResult.get(Constants.ORDER_EXTEND_INFO).toString();
    this.relatedId = mapResult.get(Constants.RESULT_UQPAY_RELATED_ID).toString();
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

  public String getRelatedId() {
    return relatedId;
  }

  public void setRelatedId(String relatedId) {
    this.relatedId = relatedId;
  }

  public int getMethodId() {
    return methodId;
  }

  public void setMethodId(int methodId) {
    this.methodId = methodId;
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
