package com.uqpay.sdk.dto.operation;

import com.uqpay.sdk.dto.ParamLink;
import com.uqpay.sdk.dto.PaygateParams;
import com.uqpay.sdk.utils.Constants;
import com.uqpay.sdk.utils.enums.SignTypeEnum;
import com.uqpay.sdk.utils.enums.UqpayTransType;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class OrderQuery implements PaygateParams {
  private static final long serialVersionUID = -9202267259550239425L;
  /**
   * is required
   */
  @ParamLink(Constants.PAY_OPTIONS_TRADE_TYPE)
  @NotNull
  private UqpayTransType tradeType = UqpayTransType.query;
  @NotEmpty
  @ParamLink(Constants.ORDER_ID)
  private String orderId; // your order id

  @ParamLink(Constants.ORDER_DATE)
  @NotNull
  private Date date; // this request generate date

  @ParamLink(Constants.AUTH_SIGN_TYPE)
  private SignTypeEnum signType = SignTypeEnum.RSA;

  public UqpayTransType getTradeType() {
    return tradeType;
  }

  public void setTradeType(UqpayTransType tradeType) {
    this.tradeType = tradeType;
  }

  public SignTypeEnum getSignType() {
    return signType;
  }

  public void setSignType(SignTypeEnum signType) {
    this.signType = signType;
  }

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
