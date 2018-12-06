package com.uqpay.sdk.dto.common;

import com.uqpay.sdk.dto.ParamLink;
import com.uqpay.sdk.dto.PaygateParams;
import com.uqpay.sdk.utils.Constants;

import javax.validation.constraints.NotBlank;

public class MerchantHostDTO implements PaygateParams {
  private static final long serialVersionUID = 3624987650141836631L;
  @ParamLink(Constants.BANK_CARD_CARD_NUM)
  @NotBlank
  private String cardNum;

  @ParamLink(Constants.BANK_CARD_EXPIRE_MONTH)
  private String expireMonth;

  @ParamLink(Constants.BANK_CARD_EXPIRE_YEAR)
  private String expireYear;

  @ParamLink(Constants.BANK_CARD_PHONE)
  @NotBlank
  private String phone;

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
