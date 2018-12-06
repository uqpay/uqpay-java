package com.uqpay.sdk.dto.merchant;

import com.uqpay.sdk.dto.common.BaseJsonRequestDTO;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class MerchantRegisterDTO extends BaseJsonRequestDTO {
  private static final long serialVersionUID = -74003103284003916L;
  @NotBlank
  private String name;
  @NotBlank
  @Size(min = 1, max = 8)
  private String abbr;

  @NotBlank
  @Email
  private String regEmail;
  @NotBlank
  private String companyName;
  @NotBlank
  private String regNo; // company register number
  @NotBlank
  private String regAddress;
  @NotBlank
  private String country;
  @NotBlank
  private String mcc;

  private String website;
  private String contact;
  private String mobile;
  @Email
  private String email; // business email

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAbbr() {
    return abbr;
  }

  public void setAbbr(String abbr) {
    this.abbr = abbr;
  }

  public String getRegEmail() {
    return regEmail;
  }

  public void setRegEmail(String regEmail) {
    this.regEmail = regEmail;
  }

  public String getCompanyName() {
    return companyName;
  }

  public void setCompanyName(String companyName) {
    this.companyName = companyName;
  }

  public String getRegNo() {
    return regNo;
  }

  public void setRegNo(String regNo) {
    this.regNo = regNo;
  }

  public String getRegAddress() {
    return regAddress;
  }

  public void setRegAddress(String regAddress) {
    this.regAddress = regAddress;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public String getMcc() {
    return mcc;
  }

  public void setMcc(String mcc) {
    this.mcc = mcc;
  }

  public String getWebsite() {
    return website;
  }

  public void setWebsite(String website) {
    this.website = website;
  }

  public String getContact() {
    return contact;
  }

  public void setContact(String contact) {
    this.contact = contact;
  }

  public String getMobile() {
    return mobile;
  }

  public void setMobile(String mobile) {
    this.mobile = mobile;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
}
