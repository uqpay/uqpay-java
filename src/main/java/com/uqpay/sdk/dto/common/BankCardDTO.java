package com.uqpay.sdk.dto.common;

import com.uqpay.sdk.dto.ParamLink;
import com.uqpay.sdk.utils.Constants;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class BankCardDTO extends CreditCardDTO {
  private static final long serialVersionUID = -6572498004474248352L;

  @ParamLink(Constants.BANK_CARD_ADDRESS_COUNTRY)
  @NotEmpty
  private String addressCountry;
  @ParamLink(Constants.BANK_CARD_ADDRESS_STATE)

  private String addressState;
  @ParamLink(Constants.BANK_CARD_ADDRESS_CITY)

  private String addressCity;
  @ParamLink(Constants.BANK_CARD_ADDRESS)

  private String address;
  @ParamLink(Constants.BANK_CARD_ZIP)
  private String zip;
  @ParamLink(Constants.BANK_CARD_EMAIL)
  @Email
  private String email;

  public String getAddressCountry() {
    return addressCountry;
  }

  public void setAddressCountry(String addressCountry) {
    this.addressCountry = addressCountry;
  }

  public String getAddressState() {
    return addressState;
  }

  public void setAddressState(String addressState) {
    this.addressState = addressState;
  }

  public String getAddressCity() {
    return addressCity;
  }

  public void setAddressCity(String addressCity) {
    this.addressCity = addressCity;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getZip() {
    return zip;
  }

  public void setZip(String zip) {
    this.zip = zip;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
}
