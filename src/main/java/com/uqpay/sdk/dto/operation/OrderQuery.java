package com.uqpay.sdk.dto.operation;

import com.uqpay.sdk.utils.UqpayTradeType;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

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

  public String getOrderId() {
    return orderId;
  }

  public void setOrderId(String orderId) {
    this.orderId = orderId;
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
}
