package com.uqpay.sdk.dto.result;

import com.uqpay.sdk.dto.ParamLink;
import com.uqpay.sdk.utils.Constants;

import java.util.Map;

public class QueryResult extends BaseResult {
  private static final long serialVersionUID = -3884357786838963487L;

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
