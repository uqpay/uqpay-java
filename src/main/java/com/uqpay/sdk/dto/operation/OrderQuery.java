package com.uqpay.sdk.dto.operation;

import com.uqpay.sdk.dto.ParamLink;
import com.uqpay.sdk.dto.PaygateParams;
import com.uqpay.sdk.utils.Constants;
import com.uqpay.sdk.utils.UqpayTradeType;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

public class OrderQuery implements PaygateParams {
  private static final long serialVersionUID = -8704248391220005204L;
  /**
   * is required
   */
  @NotEmpty
  @ParamLink(Constants.ORDER_ID)
  private String orderId; // your order id

  @ParamLink(Constants.ORDER_DATE)
  @NotNull
  private Date date; // this request generate date

  public String getOrderId() {
    return orderId;
  }

  public void setOrderId(String orderId) {
    this.orderId = orderId;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }
}
