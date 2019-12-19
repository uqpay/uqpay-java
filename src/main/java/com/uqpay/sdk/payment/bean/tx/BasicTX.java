package com.uqpay.sdk.payment.bean.tx;

import com.uqpay.sdk.bean.BasicRequest;
import com.uqpay.sdk.utils.enums.ClientType;
import com.uqpay.sdk.utils.enums.UqpayTransType;

public class BasicTX extends BasicRequest {
  private static final long serialVersionUID = 477298719669854363L;
  private UqpayTransType transType;
  private int methodId;
  private int subMerchantID;
  private ClientType clientType;
  private String clientIp;
  private String orderId;
  private double amount;
  private String currency;
  private String transName;
  private String callbackUrl;
  private int quantity;
  private int storeId;
  private int seller;

  public UqpayTransType getTransType() {
    return transType;
  }

  public void setTransType(UqpayTransType transType) {
    this.transType = transType;
  }

  public int getMethodId() {
    return methodId;
  }

  public void setMethodId(int methodId) {
    this.methodId = methodId;
  }

  public int getSubMerchantID() {
    return subMerchantID;
  }

  public void setSubMerchantID(int subMerchantID) {
    this.subMerchantID = subMerchantID;
  }

  public ClientType getClientType() {
    return clientType;
  }

  public void setClientType(ClientType clientType) {
    this.clientType = clientType;
  }

  public String getClientIp() {
    return clientIp;
  }

  public void setClientIp(String clientIp) {
    this.clientIp = clientIp;
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
