package com.uqpay.sdk.dto.operation;

import com.uqpay.sdk.utils.UqpayTradeType;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>OrderRefund class.</p>
 *
 * @author zhengwei
 * @version $Id: $Id
 */
public class OrderRefund implements Serializable {
  private static final long serialVersionUID = 5567582816994725105L;
  /**
   * is required
   */
  @NotEmpty
  private String orderId; // your order id
  @Min(0)
  private double amount;
  @NotEmpty
  private String callbackUrl; // async notice url
  @NotNull
  private UqpayTradeType tradeType;
  @NotNull
  private Date date; // this request generate date

  /**
   * not required
   */
  private String extendInfo;

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
   * <p>Getter for the field <code>callbackUrl</code>.</p>
   *
   * @return a {@link java.lang.String} object.
   */
  public String getCallbackUrl() {
    return callbackUrl;
  }

  /**
   * <p>Setter for the field <code>callbackUrl</code>.</p>
   *
   * @param callbackUrl a {@link java.lang.String} object.
   */
  public void setCallbackUrl(String callbackUrl) {
    this.callbackUrl = callbackUrl;
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
