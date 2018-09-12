package com.uqpay.sdk.dto.common;

import com.uqpay.sdk.dto.ParamLink;
import com.uqpay.sdk.utils.Constants;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Currency;
import java.util.Date;

public abstract class OrderDTO extends PayOptionsDTO {
  private static final long serialVersionUID = 1125426489037781008L;
  /**
   * is required
   */
  @ParamLink(Constants.ORDER_ID)
  @NotEmpty
  private String orderId; // your order id
  @ParamLink(Constants.ORDER_AMOUNT)
  @Positive
  private double amount;
  @ParamLink(value = Constants.ORDER_CURRENCY)
  @NotEmpty
  private Currency currency; // use ISO 4217 currency code same as the Java Currency Class
  @ParamLink(Constants.ORDER_DATE)
  @NotNull
  private Date date; // this order generate date

  /**
   * not required
   */
  @ParamLink(Constants.ORDER_QUANTITY)
  private int quantity; // quantity of products
  @ParamLink(Constants.ORDER_STORE_ID)
  private int storeId; // your store id
  @ParamLink(Constants.ORDER_SELLER)
  private int seller; // your seller id

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

  public Currency getCurrency() {
    return currency;
  }

  public void setCurrency(Currency currency) {
    this.currency = currency;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
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
}
