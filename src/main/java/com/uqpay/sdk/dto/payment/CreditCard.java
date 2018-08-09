package com.uqpay.sdk.dto.payment;

import org.hibernate.validator.constraints.CreditCardNumber;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

public class CreditCard implements Serializable {

  @NotEmpty
  private String firstName; //持卡人名
  @NotEmpty
  private String lastName; //持卡人姓
  @NotEmpty
  @CreditCardNumber
  private String cardNum; //卡号
  @NotEmpty
  private String cvv; //cvv
  @NotEmpty
  private String expireMonth; //到期时间
  @NotEmpty
  private String expireYear; //到期时间
  @NotEmpty
  private String addressCountry; //账单地址国家
  @NotEmpty
  private String addressState; //账单州、省、区域
  @NotEmpty
  private String addressCity;// 账单城市
  @NotEmpty
  private String address; //账单地址
  @NotEmpty
  private String phone; //电话号码
  @NotEmpty
  @Email
  private String email; //email
  @NotEmpty
  private String zip; //邮政编码

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

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getZip() {
    return zip;
  }

  public void setZip(String zip) {
    this.zip = zip;
  }
}
