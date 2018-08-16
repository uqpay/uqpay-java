package com.uqpay.sdk.dto.result;

import com.uqpay.sdk.utils.Constants;
import com.uqpay.sdk.utils.UqpayTradeType;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

/**
 * <p>TransResult class.</p>
 *
 * @author zhengwei
 * @version $Id: $Id
 */
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

  /**
   * <p>Constructor for TransResult.</p>
   */
  public TransResult() {}

  /**
   * <p>Constructor for TransResult.</p>
   *
   * @param mapResult a {@link java.util.Map} object.
   */
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

  /**
   * <p>Getter for the field <code>sign</code>.</p>
   *
   * @return a {@link java.lang.String} object.
   */
  public String getSign() {
    return sign;
  }

  /**
   * <p>Setter for the field <code>sign</code>.</p>
   *
   * @param sign a {@link java.lang.String} object.
   */
  public void setSign(String sign) {
    this.sign = sign;
  }

  /**
   * <p>Getter for the field <code>merchantId</code>.</p>
   *
   * @return a int.
   */
  public int getMerchantId() {
    return merchantId;
  }

  /**
   * <p>Setter for the field <code>merchantId</code>.</p>
   *
   * @param merchantId a int.
   */
  public void setMerchantId(int merchantId) {
    this.merchantId = merchantId;
  }

  /**
   * <p>Getter for the field <code>tradeType</code>.</p>
   *
   * @return a {@link com.uqpay.sdk.utils.UqpayTradeType} object.
   */
  public UqpayTradeType getTradeType() {
    return tradeType;
  }

  /**
   * <p>Setter for the field <code>tradeType</code>.</p>
   *
   * @param tradeType a {@link com.uqpay.sdk.utils.UqpayTradeType} object.
   */
  public void setTradeType(UqpayTradeType tradeType) {
    this.tradeType = tradeType;
  }

  /**
   * <p>Getter for the field <code>code</code>.</p>
   *
   * @return a {@link java.lang.String} object.
   */
  public String getCode() {
    return code;
  }

  /**
   * <p>Setter for the field <code>code</code>.</p>
   *
   * @param code a {@link java.lang.String} object.
   */
  public void setCode(String code) {
    this.code = code;
  }

  /**
   * <p>Getter for the field <code>message</code>.</p>
   *
   * @return a {@link java.lang.String} object.
   */
  public String getMessage() {
    return message;
  }

  /**
   * <p>Setter for the field <code>message</code>.</p>
   *
   * @param message a {@link java.lang.String} object.
   */
  public void setMessage(String message) {
    this.message = message;
  }

  /**
   * <p>Getter for the field <code>date</code>.</p>
   *
   * @return a {@link java.util.Date} object.
   */
  public Date getDate() {
    return date;
  }

  /**
   * <p>Setter for the field <code>date</code>.</p>
   *
   * @param date a {@link java.util.Date} object.
   */
  public void setDate(Date date) {
    this.date = date;
  }

  /**
   * <p>Getter for the field <code>orderId</code>.</p>
   *
   * @return a {@link java.lang.String} object.
   */
  public String getOrderId() {
    return orderId;
  }

  /**
   * <p>Setter for the field <code>orderId</code>.</p>
   *
   * @param orderId a {@link java.lang.String} object.
   */
  public void setOrderId(String orderId) {
    this.orderId = orderId;
  }

  /**
   * <p>Getter for the field <code>uqOrderId</code>.</p>
   *
   * @return a long.
   */
  public long getUqOrderId() {
    return uqOrderId;
  }

  /**
   * <p>Setter for the field <code>uqOrderId</code>.</p>
   *
   * @param uqOrderId a long.
   */
  public void setUqOrderId(long uqOrderId) {
    this.uqOrderId = uqOrderId;
  }

  /**
   * <p>Getter for the field <code>methodId</code>.</p>
   *
   * @return a int.
   */
  public int getMethodId() {
    return methodId;
  }

  /**
   * <p>Setter for the field <code>methodId</code>.</p>
   *
   * @param methodId a int.
   */
  public void setMethodId(int methodId) {
    this.methodId = methodId;
  }

  /**
   * <p>Getter for the field <code>amount</code>.</p>
   *
   * @return a double.
   */
  public double getAmount() {
    return amount;
  }

  /**
   * <p>Setter for the field <code>amount</code>.</p>
   *
   * @param amount a double.
   */
  public void setAmount(double amount) {
    this.amount = amount;
  }

  /**
   * <p>Getter for the field <code>currency</code>.</p>
   *
   * @return a {@link java.lang.String} object.
   */
  public String getCurrency() {
    return currency;
  }

  /**
   * <p>Setter for the field <code>currency</code>.</p>
   *
   * @param currency a {@link java.lang.String} object.
   */
  public void setCurrency(String currency) {
    this.currency = currency;
  }

  /**
   * <p>Getter for the field <code>state</code>.</p>
   *
   * @return a {@link java.lang.String} object.
   */
  public String getState() {
    return state;
  }

  /**
   * <p>Setter for the field <code>state</code>.</p>
   *
   * @param state a {@link java.lang.String} object.
   */
  public void setState(String state) {
    this.state = state;
  }

  /**
   * <p>Getter for the field <code>channelInfo</code>.</p>
   *
   * @return a {@link java.lang.String} object.
   */
  public String getChannelInfo() {
    return channelInfo;
  }

  /**
   * <p>Setter for the field <code>channelInfo</code>.</p>
   *
   * @param channelInfo a {@link java.lang.String} object.
   */
  public void setChannelInfo(String channelInfo) {
    this.channelInfo = channelInfo;
  }

  /**
   * <p>Getter for the field <code>extendInfo</code>.</p>
   *
   * @return a {@link java.lang.String} object.
   */
  public String getExtendInfo() {
    return extendInfo;
  }

  /**
   * <p>Setter for the field <code>extendInfo</code>.</p>
   *
   * @param extendInfo a {@link java.lang.String} object.
   */
  public void setExtendInfo(String extendInfo) {
    this.extendInfo = extendInfo;
  }
}
