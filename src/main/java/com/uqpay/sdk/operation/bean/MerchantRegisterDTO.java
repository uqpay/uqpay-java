package com.uqpay.sdk.operation.bean;

import com.uqpay.sdk.operation.bean.BaseJsonRequestDTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

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
  private String province;
  @NotBlank
  private String city;
  @NotBlank
  private String zipCode;
  @NotBlank
  private String addressLine1;

  private String addressLine2;
  @NotBlank
  private String businessScope;
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

  public String getProvince() {
    return province;
  }

  public void setProvince(String province) {
    this.province = province;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getZipCode() {
    return zipCode;
  }

  public void setZipCode(String zipCode) {
    this.zipCode = zipCode;
  }

  public String getAddressLine1() {
    return addressLine1;
  }

  public void setAddressLine1(String addressLine1) {
    this.addressLine1 = addressLine1;
  }

  public String getAddressLine2() {
    return addressLine2;
  }

  public void setAddressLine2(String addressLine2) {
    this.addressLine2 = addressLine2;
  }

  public String getBusinessScope() {
    return businessScope;
  }

  public void setBusinessScope(String businessScope) {
    this.businessScope = businessScope;
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
