package com.uqpay.sdk.dto.common;

import com.uqpay.sdk.dto.ParamLink;
import com.uqpay.sdk.utils.Constants;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotEmpty;

public class CreditCardDTO extends BankCardCompatibleDTO {
  private static final long serialVersionUID = -586404803233160610L;
  @ParamLink(Constants.BANK_CARD_FIRST_NAME)
  @NotEmpty
  private String firstName;
  @ParamLink(Constants.BANK_CARD_LAST_NAME)
  @NotEmpty
  private String lastName;

  @ParamLink(Constants.BANK_CARD_CVV)
  @NotEmpty
  private String cvv;

  public static CreditCardDTO valueOf(BankCardDTO bankCard) {
    CreditCardDTO result = new CreditCardDTO();
    BeanUtils.copyProperties(bankCard, result);
    return result;
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
