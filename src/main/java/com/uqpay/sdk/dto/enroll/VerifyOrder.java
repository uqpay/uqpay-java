package com.uqpay.sdk.dto.enroll;

import com.uqpay.sdk.dto.ParamLink;
import com.uqpay.sdk.dto.common.PayOptionsDTO;
import com.uqpay.sdk.utils.Constants;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class VerifyOrder extends PayOptionsDTO {
  private static final long serialVersionUID = -1264937298870841416L;
  @ParamLink(Constants.ORDER_ID)
  @NotEmpty
  private String orderId; // your order id
  @ParamLink(Constants.ORDER_DATE)
  @NotNull
  private Date date; // this order generate date

  @ParamLink(Constants.BANK_CARD_CARD_NUM)
  @NotEmpty
  private String cardNum; // the card you will enroll or pay with

  @ParamLink(Constants.BANK_CARD_PHONE)
  @NotEmpty
  private String phone;

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

  public String getCardNum() {
    return cardNum;
  }

  public void setCardNum(String cardNum) {
    this.cardNum = cardNum;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }
}
