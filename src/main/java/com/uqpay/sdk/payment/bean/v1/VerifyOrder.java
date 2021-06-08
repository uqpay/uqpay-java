package com.uqpay.sdk.payment.bean.v1;

import com.uqpay.sdk.utils.Constants;
import com.uqpay.sdk.utils.enums.UqpayTransType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.Date;

public class VerifyOrder extends PayOptionsDTO {
  private static final long serialVersionUID = 1506533610290489425L;

  @ParamLink(Constants.AUTH_MERCHANT_ID)
  private int merchantId; // only valued if your an Agent, set this value of your sub merchant id

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

  @ParamLink(Constants.PAY_OPTIONS_CLIENT_IP)
  @NotBlank
  private String clientIp; // consumer client ip

  public VerifyOrder() {
    this.setTradeType(UqpayTransType.verifycode);
  }

  public int getMerchantId() {
    return merchantId;
  }

  public void setMerchantId(int merchantId) {
    this.merchantId = merchantId;
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
