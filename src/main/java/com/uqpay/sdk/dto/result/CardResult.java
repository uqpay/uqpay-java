package com.uqpay.sdk.dto.result;

import com.uqpay.sdk.utils.Constants;

import java.util.Map;

public class CardResult extends TransResult {
  private String channelCode;
  private String channelMsg;

  public CardResult() {super();}
  public CardResult(Map<String, Object> mapResult) {
    super(mapResult);
    this.channelCode = mapResult.get(Constants.RESULT_CHANNEL_CODE).toString();
    this.channelMsg = mapResult.get(Constants.RESULT_CHANNEL_MESSAGE).toString();
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
}
