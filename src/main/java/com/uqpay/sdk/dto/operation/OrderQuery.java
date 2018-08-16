package com.uqpay.sdk.dto.operation;

import com.uqpay.sdk.utils.UqpayTradeType;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>OrderQuery class.</p>
 *
 * @author zhengwei
 * @version $Id: $Id
 */
public class OrderQuery implements Serializable {
  private static final long serialVersionUID = -2686846167963889259L;
  /**
   * is required
   */
  @NotEmpty
  private String orderId; // your order id

  @NotNull
  private UqpayTradeType tradeType;

  @NotNull
  private Date date; // this request generate date

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
}
