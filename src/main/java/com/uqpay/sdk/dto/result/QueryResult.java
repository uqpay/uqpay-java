package com.uqpay.sdk.dto.result;

import com.uqpay.sdk.dto.ParamLink;
import com.uqpay.sdk.utils.Constants;
import com.uqpay.sdk.utils.UqpayTradeType;
import java.util.Date;
import java.util.Map;

public class QueryResult extends BaseResult {
  private static final long serialVersionUID = -3884357786838963487L;
  @ParamLink(Constants.AUTH_SIGN)
  private String sign;
  @ParamLink(Constants.AUTH_MERCHANT_ID)
  private int merchantId;
  @ParamLink(Constants.AUTH_AGENT_ID)
  private int agentId;
  @ParamLink(Constants.PAY_OPTIONS_METHOD_ID)
  private int methodId;
  @ParamLink(Constants.PAY_OPTIONS_TRADE_TYPE)
  private UqpayTradeType tradeType;
  @ParamLink(Constants.ORDER_DATE)
  private Date date;
  @ParamLink(Constants.ORDER_ID)
  private String orderId;
  @ParamLink(Constants.RESULT_UQPAY_ORDER_ID)
  private long uqOrderId;
  @ParamLink(Constants.RESULT_UQPAY_RELATED_ID)
  private String relatedId; // related order id (uqpay order), if this is a refund/cancel/withdraw order
  @ParamLink(Constants.RESULT_STATE)
  private String state;
  @ParamLink(value = Constants.ORDER_EXTEND_INFO, targetType = "JSON")
  private Map<String,String> channelInfo;
  @ParamLink(value = Constants.ORDER_CHANNEL_INFO, targetType = "JSON")
  private Map<String,String> extendInfo;

  public QueryResult() {
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

  public int getAgentId() {
    return agentId;
  }

  public void setAgentId(int agentId) {
    this.agentId = agentId;
  }

  public int getMethodId() {
    return methodId;
  }

  public void setMethodId(int methodId) {
    this.methodId = methodId;
  }

  public UqpayTradeType getTradeType() {
    return tradeType;
  }

  public void setTradeType(UqpayTradeType tradeType) {
    this.tradeType = tradeType;
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
