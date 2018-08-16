package com.uqpay.sdk.vo;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>UqpayCashier class.</p>
 *
 * @author zhengwei
 * @version $Id: $Id
 */
public class UqpayCashier implements ParamsMap {
  private Integer merchantId;//	商户ID， 商户在UQPAY系统中的唯一标识
  private String tradeType; //	交易类型，详见附录1
  private Date date;	//请求的时间，使用Unix时间戳
  private String orderId; //商户订单号
  private double amount;
  private String currency; //币种
  private String transName; //商品信息
  private String callbackUrl; //回调地址
  private String returnUrl;//同步跳转URL
  private int quantity = 0; //商品数量
  private int storeId = 0; //店铺ID
  private int seller = 0; //销售员ID
  private String channelInfo;
  private String extendInfo;

  /** {@inheritDoc} */
  @Override
  public Map<String, String> getParamsMap() {
    Map<String, String> paramsMap = new HashMap<>();
    paramsMap.put("merchantId", merchantId.toString());
    paramsMap.put("tradeType", tradeType);
    paramsMap.put("date", String.valueOf(date.getTime()));
    paramsMap.put("orderId", orderId);
    if (amount > 0) {
      paramsMap.put("amount", String.valueOf(amount));
    }
    paramsMap.put("currency", currency);
    paramsMap.put("transName", transName);
    paramsMap.put("callbackUrl", callbackUrl);
    paramsMap.put("returnUrl", returnUrl);
    if (quantity > 0) {
      paramsMap.put("quantity", String.valueOf(quantity));
    }
    if (storeId > 0) {
      paramsMap.put("storeId", String.valueOf(storeId));
    }
    if (seller > 0) {
      paramsMap.put("seller", String.valueOf(seller));
    }
    if (channelInfo != null && !channelInfo.equals("")) {
      paramsMap.put("channelInfo", channelInfo);
    }
    if (extendInfo != null && !extendInfo.equals("")) {
      paramsMap.put("extendInfo", extendInfo);
    }
    return paramsMap;
  }

  /**
   * <p>Getter for the field <code>merchantId</code>.</p>
   *
   * @return a {@link java.lang.Integer} object.
   */
  public Integer getMerchantId() {
    return merchantId;
  }

  /**
   * <p>Setter for the field <code>merchantId</code>.</p>
   *
   * @param merchantId a {@link java.lang.Integer} object.
   */
  public void setMerchantId(Integer merchantId) {
    this.merchantId = merchantId;
  }

  /**
   * <p>Getter for the field <code>tradeType</code>.</p>
   *
   * @return a {@link java.lang.String} object.
   */
  public String getTradeType() {
    return tradeType;
  }

  /**
   * <p>Setter for the field <code>tradeType</code>.</p>
   *
   * @param tradeType a {@link java.lang.String} object.
   */
  public void setTradeType(String tradeType) {
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
   * <p>Getter for the field <code>returnUrl</code>.</p>
   *
   * @return a {@link java.lang.String} object.
   */
  public String getReturnUrl() {
    return returnUrl;
  }

  /**
   * <p>Setter for the field <code>returnUrl</code>.</p>
   *
   * @param returnUrl a {@link java.lang.String} object.
   */
  public void setReturnUrl(String returnUrl) {
    this.returnUrl = returnUrl;
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
