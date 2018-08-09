package com.uqpay.sdk.dto.operation;

import com.uqpay.sdk.utils.UqpayTradeType;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

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

  public String getOrderId() {
    return orderId;
  }

  public void setOrderId(String orderId) {
    this.orderId = orderId;
  }

  public double getAmount() {
    return amount;
  }

  public void setAmount(double amount) {
    this.amount = amount;
  }

  public String getCallbackUrl() {
    return callbackUrl;
  }

  public void setCallbackUrl(String callbackUrl) {
    this.callbackUrl = callbackUrl;
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

  public String getExtendInfo() {
    return extendInfo;
  }

  public void setExtendInfo(String extendInfo) {
    this.extendInfo = extendInfo;
  }
}
