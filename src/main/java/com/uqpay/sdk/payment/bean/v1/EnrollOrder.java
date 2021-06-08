package com.uqpay.sdk.payment.bean.v1;

import com.uqpay.sdk.utils.Constants;
import com.uqpay.sdk.utils.enums.UqpayTransType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.util.Date;

public class EnrollOrder extends PayOptionsDTO {
  private static final long serialVersionUID = -1795060100830097994L;

  @ParamLink(Constants.AUTH_MERCHANT_ID)
  private int merchantId; // only valued if your an Agent, set this value of your sub merchant id

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

  @ParamLink(Constants.BANK_CARD_CARD_NUM)
  @NotBlank
  private String cardNum;

  @ParamLink(Constants.BANK_CARD_CVV)
  private String cvv;

  @ParamLink(Constants.BANK_CARD_EXPIRE_MONTH)
  private String expireMonth;

  @ParamLink(Constants.BANK_CARD_EXPIRE_YEAR)
  private String expireYear;

  @ParamLink(Constants.BANK_CARD_PHONE)
  private String phone;

  @ParamLink(Constants.PAY_OPTIONS_CLIENT_IP)
  @NotBlank
  private String clientIp; // consumer client ip

  public EnrollOrder() {
    this.setTradeType(UqpayTransType.enroll);
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

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getCardNum() {
    return cardNum;
  }

  public void setCardNum(String cardNum) {
    this.cardNum = cardNum;
  }

  public String getCvv() {
    return cvv;
  }

  public void setCvv(String cvv) {
    this.cvv = cvv;
  }

  public String getExpireMonth() {
    return expireMonth;
  }

  public void setExpireMonth(String expireMonth) {
    this.expireMonth = expireMonth;
  }

  public String getExpireYear() {
    return expireYear;
  }

  public void setExpireYear(String expireYear) {
    this.expireYear = expireYear;
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
