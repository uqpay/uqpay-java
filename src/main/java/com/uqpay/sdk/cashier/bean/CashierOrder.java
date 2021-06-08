package com.uqpay.sdk.cashier.bean;

import com.uqpay.sdk.payment.bean.v1.ParamLink;
import com.uqpay.sdk.payment.bean.v1.PaygateParams;
import com.uqpay.sdk.utils.Constants;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.util.Date;

public class CashierOrder implements PaygateParams {
  /**
   * is required
   */
  @ParamLink(Constants.ORDER_TRANS_NAME)
  @NotEmpty
  private String transName; // product info
  @ParamLink(Constants.ORDER_ID)
  @NotEmpty
  private String orderId; // your order id
  @ParamLink(Constants.ORDER_AMOUNT)
  @Positive
  private double amount;
  @ParamLink(value = Constants.ORDER_CURRENCY)
  @NotNull
  private String currency; // use ISO 4217 currency code same as the Java Currency Class
  @ParamLink(Constants.ORDER_DATE)
  @NotNull
  private Date date; // this order generate date
  @ParamLink(Constants.PAY_OPTIONS_ASYNC_NOTICE_URL)
  @NotEmpty
  private String callbackUrl; // async notice url
  @ParamLink(Constants.PAY_OPTIONS_SYNC_NOTICE_URL)
  @NotEmpty
  private String returnUrl; // sync notice url

  @ParamLink(Constants.AUTH_MERCHANT_ID)
  private int merchantId; // only valued if your an Agent, set this value of your sub merchant id

  /**
   * not required
   */

  @ParamLink(Constants.ORDER_QUANTITY)
  private int quantity; // quantity of products
  @ParamLink(Constants.ORDER_STORE_ID)
  private String storeId; // your store info
  @ParamLink(Constants.ORDER_SELLER)
  private String seller; // your seller info
  @ParamLink(Constants.ORDER_EXTEND_INFO_ISSUER_LOCALE)
  private String locale; // issuer locale. see Auto DCC

  public String getLocale() {
    return locale;
  }

  public void setLocale(String locale) {
    this.locale = locale;
  }

  public int getMerchantId() {
    return merchantId;
  }

  public void setMerchantId(int merchantId) {
    this.merchantId = merchantId;
  }

  public String getTransName() {
    return transName;
  }

  public void setTransName(String transName) {
    this.transName = transName;
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

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public String getCallbackUrl() {
    return callbackUrl;
  }

  public void setCallbackUrl(String callbackUrl) {
    this.callbackUrl = callbackUrl;
  }

  public String getReturnUrl() {
    return returnUrl;
  }

  public void setReturnUrl(String returnUrl) {
    this.returnUrl = returnUrl;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public String getStoreId() {
    return storeId;
  }

  public void setStoreId(String storeId) {
    this.storeId = storeId;
  }

  public String getSeller() {
    return seller;
  }

  public void setSeller(String seller) {
    this.seller = seller;
  }
}
