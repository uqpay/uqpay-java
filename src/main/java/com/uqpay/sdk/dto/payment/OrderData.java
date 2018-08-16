package com.uqpay.sdk.dto.payment;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * Order Data
 * all this info will be return to your system when the pay request is done
 *
 * @author zhengwei
 * @version $Id: $Id
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
   * <p>Getter for the field <code>transName</code>.</p>
   *
   * @return a {@link java.lang.String} object.
   */
  public String getTransName() {
    return transName;
  }

  /**
   * <p>Setter for the field <code>transName</code>.</p>
   *
   * @param transName a {@link java.lang.String} object.
   */
  public void setTransName(String transName) {
    this.transName = transName;
  }

  /**
   * <p>Getter for the field <code>quantity</code>.</p>
   *
   * @return a int.
   */
  public int getQuantity() {
    return quantity;
  }

  /**
   * <p>Setter for the field <code>quantity</code>.</p>
   *
   * @param quantity a int.
   */
  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  /**
   * <p>Getter for the field <code>storeId</code>.</p>
   *
   * @return a int.
   */
  public int getStoreId() {
    return storeId;
  }

  /**
   * <p>Setter for the field <code>storeId</code>.</p>
   *
   * @param storeId a int.
   */
  public void setStoreId(int storeId) {
    this.storeId = storeId;
  }

  /**
   * <p>Getter for the field <code>seller</code>.</p>
   *
   * @return a int.
   */
  public int getSeller() {
    return seller;
  }

  /**
   * <p>Setter for the field <code>seller</code>.</p>
   *
   * @param seller a int.
   */
  public void setSeller(int seller) {
    this.seller = seller;
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
