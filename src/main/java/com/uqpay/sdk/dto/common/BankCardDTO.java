package com.uqpay.sdk.dto.common;

import com.uqpay.sdk.dto.ParamLink;
import com.uqpay.sdk.dto.PaygateParams;
import com.uqpay.sdk.utils.Constants;

import javax.validation.constraints.NotBlank;

public class BankCardDTO implements PaygateParams {
  private static final long serialVersionUID = -15914608351693804L;
  @ParamLink(Constants.BANK_CARD_FIRST_NAME)
  @NotBlank
  private String firstName;
  @ParamLink(Constants.BANK_CARD_LAST_NAME)
  @NotBlank
  private String lastName;

  @ParamLink(Constants.BANK_CARD_CVV)
  @NotBlank
  private String cvv;

  @ParamLink(Constants.BANK_CARD_CARD_NUM)
  @NotBlank
  private String cardNum;

  @ParamLink(Constants.BANK_CARD_EXPIRE_MONTH)
  @NotBlank
  private String expireMonth;

  @ParamLink(Constants.BANK_CARD_EXPIRE_YEAR)
  @NotBlank
  private String expireYear;

  public static BankCardDTO valueOf(BankCardExtendDTO bankCard) {
    BankCardDTO result = new BankCardDTO();
    result.setFirstName(bankCard.getFirstName());
    result.setLastName(bankCard.getLastName());
    result.setCvv(bankCard.getCvv());
    result.setCardNum(bankCard.getCardNum());
    result.setExpireMonth(bankCard.getExpireMonth());
    result.setExpireYear(bankCard.getExpireYear());
    return result;
  }

  public String getCardNum() {
    return cardNum;
  }

  public void setCardNum(String cardNum) {
    this.cardNum = cardNum;
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

  public String getCvv() {
    return cvv;
  }

  public void setCvv(String cvv) {
    this.cvv = cvv;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }
}
