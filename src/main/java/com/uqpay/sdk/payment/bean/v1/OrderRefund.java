package com.uqpay.sdk.payment.bean.v1;

import com.uqpay.sdk.utils.Constants;
import com.uqpay.sdk.utils.enums.ClientType;
import com.uqpay.sdk.utils.enums.UqpayTransType;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.util.Date;
import java.util.Map;

public class OrderRefund implements PaygateParams {
  private static final long serialVersionUID = -8195330516344377187L;
  /**
   * is required
   */
  @ParamLink(Constants.PAY_OPTIONS_TRADE_TYPE)
  @NotNull
  private UqpayTransType tradeType = UqpayTransType.refund;
  @ParamLink(Constants.ORDER_ID)
  @NotEmpty
  private String orderId; // your order id
  @ParamLink(Constants.RESULT_UQPAY_ORDER_ID)
  @Positive
  private long uqOrderId; // 原始订单的UQPAY 订单号
  @ParamLink(Constants.ORDER_AMOUNT)
  @Positive
  private double amount;
  @ParamLink(Constants.PAY_OPTIONS_ASYNC_NOTICE_URL)
  @NotEmpty
  private String callbackUrl; // async notice url
  @ParamLink(Constants.ORDER_DATE)
  @NotNull
  private Date date; // this request generate date
  @ParamLink(Constants.PAY_OPTIONS_CLIENT_TYPE)
  @NotNull
  private ClientType clientType = ClientType.Web;

  @ParamLink(Constants.AUTH_MERCHANT_ID)
  private int merchantId; // only valued if your an Agent, set this value of your sub merchant id

  /**
   * not required
   */
  @ParamLink(value = Constants.ORDER_EXTEND_INFO, targetType = "JSON")
  private Map<String, String> extendInfo;

  public int getMerchantId() {
    return merchantId;
  }

  public void setMerchantId(int merchantId) {
    this.merchantId = merchantId;
  }

  public long getUqOrderId() {
    return uqOrderId;
  }

  public void setUqOrderId(long uqOrderId) {
    this.uqOrderId = uqOrderId;
  }

  public UqpayTransType getTradeType() {
    return tradeType;
  }

  public void setTradeType(UqpayTransType tradeType) {
    this.tradeType = tradeType;
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

  public String getCallbackUrl() {
    return callbackUrl;
  }

  public void setCallbackUrl(String callbackUrl) {
    this.callbackUrl = callbackUrl;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public ClientType getClientType() {
    return clientType;
  }

  public void setClientType(ClientType clientType) {
    this.clientType = clientType;
  }

  public Map<String, String> getExtendInfo() {
    return extendInfo;
  }

  public void setExtendInfo(Map<String, String> extendInfo) {
    this.extendInfo = extendInfo;
  }
}
