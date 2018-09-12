package com.uqpay.sdk.dto.enroll;

import com.uqpay.sdk.dto.ParamLink;
import com.uqpay.sdk.dto.common.PayOptionsDTO;
import com.uqpay.sdk.utils.Constants;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Date;

public class EnrollOrder extends PayOptionsDTO {
  private static final long serialVersionUID = 321034190381046477L;
  @ParamLink(Constants.ORDER_ID)
  @NotEmpty
  private String orderId; // your order id
  @ParamLink(Constants.ORDER_DATE)
  @NotNull
  private Date date; // this order generate date

  @ParamLink(Constants.MERCHANT_HOST_VERIFY_CODE)
  @NotNull
  private String verifyCode; // the verify code you get after request the verify api

  @ParamLink(Constants.MERCHANT_HOST_ENROLL_CODE_UQPAY_ID)
  @Positive
  private long codeOrderId;// the uqpay order id, when you request for the verify code you will get it

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

  public String getVerifyCode() {
    return verifyCode;
  }

  public void setVerifyCode(String verifyCode) {
    this.verifyCode = verifyCode;
  }

  public long getCodeOrderId() {
    return codeOrderId;
  }

  public void setCodeOrderId(long codeOrderId) {
    this.codeOrderId = codeOrderId;
  }
}
