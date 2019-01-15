package com.uqpay.sdk.dto.result;

import com.uqpay.sdk.dto.ParamLink;
import com.uqpay.sdk.utils.Constants;

public class EnrollResult extends BaseResult {
  private static final long serialVersionUID = 328774373720863051L;
  @ParamLink(value = Constants.SERVER_HOST_CARD_TOKEN)
  protected String token; // this field has value when the ServerHost methods
  @ParamLink(Constants.RESULT_CHANNEL_CODE)
  private String channelCode;
  @ParamLink(Constants.RESULT_CHANNEL_MESSAGE)
  private String channelMsg;
  @ParamLink(Constants.ORDER_ID)
  private String orderId;
  @ParamLink(Constants.RESULT_UQPAY_ORDER_ID)
  private long uqOrderId; // this order id generate by uqpay

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
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
}
