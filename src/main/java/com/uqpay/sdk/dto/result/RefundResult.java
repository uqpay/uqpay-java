package com.uqpay.sdk.dto.result;

import com.uqpay.sdk.utils.Constants;
import com.uqpay.sdk.utils.UqpayTradeType;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

/**
 * <p>RefundResult class.</p>
 *
 * @author zhengwei
 * @version $Id: $Id
 */
public class RefundResult implements Serializable{
  private static final long serialVersionUID = -6320236770349138351L;
  private String sign;		    //签名（参见3.签名与传参）
  private int merchantId;
  private UqpayTradeType tradeType;		//交易类型，详见附录1
  private String code;		    //结果码
  private String message;		//结果描述
  private Date date;		    //请求的时间，使用Unix时间戳

  private String orderId;		//商户订单ID，在商户端唯一
  private long uqOrderId;	    //UQPAY端的订单ID
  private double amount;		//订单金额
  private String state;			//订单状态
  private String extendInfo;	//商户订单传入的Json编码的扩展信息

  /**
   * <p>Constructor for RefundResult.</p>
   */
  public RefundResult() {}

  /**
   * <p>Constructor for RefundResult.</p>
   *
   * @param mapResult a {@link java.util.Map} object.
   */
  public RefundResult(Map<String, Object> mapResult) {
    this.code = (String) mapResult.get(Constants.RESULT_CODE);
    this.sign = (String) mapResult.get(Constants.AUTH_SIGN);
    this.merchantId = Integer.valueOf(mapResult.get(Constants.AUTH_MERCHANT_ID).toString());
    this.tradeType = UqpayTradeType.valueOf((String) mapResult.get(Constants.PAY_OPTIONS_TRADE_TYPE));
    this.message = (String) mapResult.get(Constants.RESULT_MESSAGE);
    this.date = new Date(Long.valueOf(mapResult.get(Constants.ORDER_DATE).toString()));
    this.orderId = (String) mapResult.get(Constants.ORDER_ID);
    this.uqOrderId = Long.valueOf(mapResult.get(Constants.RESULT_UQPAY_ORDER_ID).toString());
    this.amount = Double.valueOf(mapResult.get(Constants.ORDER_AMOUNT).toString());
    this.state = (String) mapResult.get(Constants.RESULT_STATE);
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
