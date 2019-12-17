package com.uqpay.sdk.payment.bean.v1;

import com.uqpay.sdk.utils.Constants;
import com.uqpay.sdk.utils.enums.UqpayTransType;

import java.util.Date;
import java.util.Map;

public class RefundResult extends BaseResult{
  private static final long serialVersionUID = 226207824956162475L;
  @ParamLink(Constants.AUTH_SIGN)
  private String sign;
  @ParamLink(Constants.AUTH_MERCHANT_ID)
  private int merchantId;
  @ParamLink(Constants.AUTH_AGENT_ID)
  private int agentId;
  @ParamLink(Constants.PAY_OPTIONS_TRADE_TYPE)
  private UqpayTransType transType;
  @ParamLink(Constants.ORDER_DATE)
  private Date date;

  @ParamLink(Constants.ORDER_ID)
  private String orderId;
  @ParamLink(Constants.RESULT_UQPAY_ORDER_ID)
  private long uqOrderId;
  @ParamLink(Constants.ORDER_AMOUNT)
  private double amount;
  @ParamLink(Constants.RESULT_STATE)
  private String state;

  @ParamLink(value = Constants.ORDER_CHANNEL_INFO, targetType = "JSON")
  private Map<String,String> extendInfo;

  public RefundResult() {}

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

  public UqpayTransType getTransType() {
    return transType;
  }

  public void setTransType(UqpayTransType transType) {
    this.transType = transType;
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

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public Map<String, String> getExtendInfo() {
    return extendInfo;
  }

  public void setExtendInfo(Map<String, String> extendInfo) {
    this.extendInfo = extendInfo;
  }
}
