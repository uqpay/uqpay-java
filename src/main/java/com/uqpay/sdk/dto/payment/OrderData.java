package com.uqpay.sdk.dto.payment;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * Order Data
 * all this info will be return to your system when the pay request is done
 */
public class OrderData implements Serializable {
  private static final long serialVersionUID = -3505786841126404244L;
  /**
   * is required
   */
  @NotEmpty
  private String orderId; // your order id
  @Min(0)
  private double amount;
  @NotEmpty
  private String currency; // use ISO 4217 currency code same as the Java Currency Class
  @NotEmpty
  private String transName; // product info
  @NotNull
  private Date date; // this order generate date


  /**
   * not required
   */
  private int quantity; // quantity of products
  private int storeId; // your store id
  private int seller; // your seller id
  private String channelInfo; // extend info which the payment channel need
  private String extendInfo; // other extend info

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

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

  public String getCurrency() {
    return currency;
  }

  public void setCurrency(String currency) {
    this.currency = currency;
  }

  public String getTransName() {
    return transName;
  }

  public void setTransName(String transName) {
    this.transName = transName;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public int getStoreId() {
    return storeId;
  }

  public void setStoreId(int storeId) {
    this.storeId = storeId;
  }

  public int getSeller() {
    return seller;
  }

  public void setSeller(int seller) {
    this.seller = seller;
  }

  public String getChannelInfo() {
    return channelInfo;
  }

  public void setChannelInfo(String channelInfo) {
    this.channelInfo = channelInfo;
  }

  public String getExtendInfo() {
    return extendInfo;
  }

  public void setExtendInfo(String extendInfo) {
    this.extendInfo = extendInfo;
  }
}
