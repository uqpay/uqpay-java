package com.uqpay.sdk.dto.result;

import com.uqpay.sdk.utils.Constants;

import java.util.Map;

/**
 * <p>CardResult class.</p>
 *
 * @author zhengwei
 * @version $Id: $Id
 */
public class CardResult extends TransResult {
  private String channelCode;
  private String channelMsg;

  /**
   * <p>Constructor for CardResult.</p>
   */
  public CardResult() {super();}
  /**
   * <p>Constructor for CardResult.</p>
   *
   * @param mapResult a {@link java.util.Map} object.
   */
  public CardResult(Map<String, Object> mapResult) {
    super(mapResult);
    this.channelCode = mapResult.get(Constants.RESULT_CHANNEL_CODE).toString();
    this.channelMsg = mapResult.get(Constants.RESULT_CHANNEL_MESSAGE).toString();
  }

  /**
   * <p>Getter for the field <code>channelCode</code>.</p>
   *
   * @return a {@link java.lang.String} object.
   */
  public String getChannelCode() {
    return channelCode;
  }

  /**
   * <p>Setter for the field <code>channelCode</code>.</p>
   *
   * @param channelCode a {@link java.lang.String} object.
   */
  public void setChannelCode(String channelCode) {
    this.channelCode = channelCode;
  }

  /**
   * <p>Getter for the field <code>channelMsg</code>.</p>
   *
   * @return a {@link java.lang.String} object.
   */
  public String getChannelMsg() {
    return channelMsg;
  }

  /**
   * <p>Setter for the field <code>channelMsg</code>.</p>
   *
   * @param channelMsg a {@link java.lang.String} object.
   */
  public void setChannelMsg(String channelMsg) {
    this.channelMsg = channelMsg;
  }
}
