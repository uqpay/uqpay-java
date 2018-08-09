package com.uqpay.sdk.vo;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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

  public Integer getMerchantId() {
    return merchantId;
  }

  public void setMerchantId(Integer merchantId) {
    this.merchantId = merchantId;
  }

  public String getTradeType() {
    return tradeType;
  }

  public void setTradeType(String tradeType) {
    this.tradeType = tradeType;
  }

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
