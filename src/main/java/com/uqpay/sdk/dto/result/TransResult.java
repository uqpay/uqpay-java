package com.uqpay.sdk.dto.result;

import com.uqpay.sdk.dto.ParamLink;
import com.uqpay.sdk.utils.Constants;
import com.uqpay.sdk.utils.UqpayTradeType;
import java.util.Currency;
import java.util.Date;
import java.util.Map;

public class TransResult extends BaseResult {
  @ParamLink(Constants.AUTH_SIGN)
  private String sign;
  @ParamLink(Constants.AUTH_MERCHANT_ID)
  private int merchantId;
  @ParamLink(Constants.AUTH_AGENT_ID)
  private int agentId;
  @ParamLink(Constants.PAY_OPTIONS_TRADE_TYPE)
  private UqpayTradeType tradeType;
  @ParamLink(Constants.PAY_OPTIONS_METHOD_ID)
  private int methodId;
  @ParamLink(Constants.ORDER_DATE)
  private Date date;
  @ParamLink(Constants.ORDER_ID)
  private String orderId;
  @ParamLink(Constants.RESULT_UQPAY_ORDER_ID)
  private long uqOrderId; // this order id generate by uqpay
  @ParamLink(Constants.ORDER_AMOUNT)
  private double amount;
  @ParamLink(value = Constants.ORDER_CURRENCY)
  private Currency currency;
  @ParamLink(value = Constants.RESULT_STATE)
  private String state; // order state
  @ParamLink(value = Constants.SERVER_HOST_CARD_TOKEN)
  protected String token;       //cardtoken
  @ParamLink(value = Constants.ORDER_EXTEND_INFO, targetType = "JSON")
  private Map<String,String> channelInfo;
  @ParamLink(value = Constants.ORDER_CHANNEL_INFO, targetType = "JSON")
  private Map<String,String> extendInfo;

  public TransResult() {}

  public String getSign() {
    return sign;
  }

  public void setSign(String sign) {
    this.sign = sign;
  }

  public int getMerchantId() {
    return merchantId;
  }

  public void setMerchantId(int merchantId) {
    this.merchantId = merchantId;
  }

  public int getAgentId() {
    return agentId;
  }

  public void setAgentId(int agentId) {
    this.agentId = agentId;
  }

  public UqpayTradeType getTradeType() {
    return tradeType;
  }

  public void setTradeType(UqpayTradeType tradeType) {
    this.tradeType = tradeType;
  }

  public int getMethodId() {
    return methodId;
  }

  public void setMethodId(int methodId) {
    this.methodId = methodId;
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

  public long getUqOrderId() {
    return uqOrderId;
  }

  public void setUqOrderId(long uqOrderId) {
    this.uqOrderId = uqOrderId;
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

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public Map<String, String> getChannelInfo() {
    return channelInfo;
  }

  public void setChannelInfo(Map<String, String> channelInfo) {
    this.channelInfo = channelInfo;
  }

  public Map<String, String> getExtendInfo() {
    return extendInfo;
  }

  public void setExtendInfo(Map<String, String> extendInfo) {
    this.extendInfo = extendInfo;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }
}
