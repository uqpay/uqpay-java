package com.uqpay.sdk.payment.bean.v1;

import com.uqpay.sdk.utils.Constants;
import com.uqpay.sdk.utils.enums.UqpayTransType;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import java.util.Date;

public class OrderQuery implements PaygateParams {
  private static final long serialVersionUID = -759803325716385809L;
  /**
   * is required
   */
  @ParamLink(Constants.PAY_OPTIONS_TRADE_TYPE)
  @NotNull
  private UqpayTransType tradeType = UqpayTransType.query;

  @ParamLink(Constants.ORDER_DATE)
  @NotNull
  private Date date; // this request generate date

  /**
   * required rule: orderId not empty || uqOrderId not empty
   */

  @ParamLink(Constants.ORDER_ID)
  private String orderId; // your origin order id

  @ParamLink(Constants.RESULT_UQPAY_ORDER_ID)
  @PositiveOrZero
  private long uqOrderId; // the uqpay order id

  @ParamLink(Constants.AUTH_MERCHANT_ID)
  private int merchantId; // only valued if your an Agent, set this value of your sub merchant id

  public int getMerchantId() {
    return merchantId;
  }

  public void setMerchantId(int merchantId) {
    this.merchantId = merchantId;
  }

  public String getOrderId() {
    return orderId;
  }

  public void setOrderId(String orderId) {
    this.orderId = orderId;
  }

  public UqpayTransType getTradeType() {
    return tradeType;
  }

  public void setTradeType(UqpayTransType tradeType) {
    this.tradeType = tradeType;
  }

  public long getUqOrderId() {
    return uqOrderId;
  }

  public void setUqOrderId(long uqOrderId) {
    this.uqOrderId = uqOrderId;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }
}
