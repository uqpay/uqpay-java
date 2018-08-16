package com.uqpay.sdk.dto.result;

import com.uqpay.sdk.dto.ParamLink;
import com.uqpay.sdk.utils.Constants;

public class CardResult extends TransResult {
  @ParamLink(Constants.RESULT_CHANNEL_CODE)
  private String channelCode;
  @ParamLink(Constants.RESULT_CHANNEL_MESSAGE)
  private String channelMsg;

  public CardResult() {super();}

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
}
