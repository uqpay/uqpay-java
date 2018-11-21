package com.uqpay.sdk.dto.common;

import com.uqpay.sdk.dto.PaygateParams;
import com.uqpay.sdk.dto.ParamLink;
import com.uqpay.sdk.utils.BankCardType;
import com.uqpay.sdk.utils.Constants;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class BankCardCompatibleDTO extends BankCardHostBaseDTO {
  private static final long serialVersionUID = -3250705245010408049L;

  @ParamLink(Constants.BANK_CARD_CARD_NUM)
  @NotEmpty
  private String cardNum;

  @ParamLink(Constants.BANK_CARD_EXPIRE_MONTH)
  private String expireMonth;

  @ParamLink(Constants.BANK_CARD_EXPIRE_YEAR)
  private String expireYear;

  @ParamLink(Constants.BANK_CARD_PHONE)
  @NotEmpty
  private String phone;

  public BankCardCompatibleDTO() {
  }

  public static BankCardCompatibleDTO valueOf(BankCardDTO bankCard) {
    BankCardCompatibleDTO result = new BankCardCompatibleDTO();
    BeanUtils.copyProperties(bankCard, result);
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

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }
}
