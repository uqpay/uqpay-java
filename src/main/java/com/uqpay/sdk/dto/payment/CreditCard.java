package com.uqpay.sdk.dto.payment;

import org.hibernate.validator.constraints.CreditCardNumber;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * <p>CreditCard class.</p>
 *
 * @author zhengwei
 * @version $Id: $Id
 */
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

  /**
   * <p>Getter for the field <code>firstName</code>.</p>
   *
   * @return a {@link java.lang.String} object.
   */
  public String getFirstName() {
    return firstName;
  }

  /**
   * <p>Setter for the field <code>firstName</code>.</p>
   *
   * @param firstName a {@link java.lang.String} object.
   */
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  /**
   * <p>Getter for the field <code>lastName</code>.</p>
   *
   * @return a {@link java.lang.String} object.
   */
  public String getLastName() {
    return lastName;
  }

  /**
   * <p>Setter for the field <code>lastName</code>.</p>
   *
   * @param lastName a {@link java.lang.String} object.
   */
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  /**
   * <p>Getter for the field <code>cardNum</code>.</p>
   *
   * @return a {@link java.lang.String} object.
   */
  public String getCardNum() {
    return cardNum;
  }

  /**
   * <p>Setter for the field <code>cardNum</code>.</p>
   *
   * @param cardNum a {@link java.lang.String} object.
   */
  public void setCardNum(String cardNum) {
    this.cardNum = cardNum;
  }

  /**
   * <p>Getter for the field <code>cvv</code>.</p>
   *
   * @return a {@link java.lang.String} object.
   */
  public String getCvv() {
    return cvv;
  }

  /**
   * <p>Setter for the field <code>cvv</code>.</p>
   *
   * @param cvv a {@link java.lang.String} object.
   */
  public void setCvv(String cvv) {
    this.cvv = cvv;
  }

  /**
   * <p>Getter for the field <code>expireMonth</code>.</p>
   *
   * @return a {@link java.lang.String} object.
   */
  public String getExpireMonth() {
    return expireMonth;
  }

  /**
   * <p>Setter for the field <code>expireMonth</code>.</p>
   *
   * @param expireMonth a {@link java.lang.String} object.
   */
  public void setExpireMonth(String expireMonth) {
    this.expireMonth = expireMonth;
  }

  /**
   * <p>Getter for the field <code>expireYear</code>.</p>
   *
   * @return a {@link java.lang.String} object.
   */
  public String getExpireYear() {
    return expireYear;
  }

  /**
   * <p>Setter for the field <code>expireYear</code>.</p>
   *
   * @param expireYear a {@link java.lang.String} object.
   */
  public void setExpireYear(String expireYear) {
    this.expireYear = expireYear;
  }

  /**
   * <p>Getter for the field <code>addressCountry</code>.</p>
   *
   * @return a {@link java.lang.String} object.
   */
  public String getAddressCountry() {
    return addressCountry;
  }

  /**
   * <p>Setter for the field <code>addressCountry</code>.</p>
   *
   * @param addressCountry a {@link java.lang.String} object.
   */
  public void setAddressCountry(String addressCountry) {
    this.addressCountry = addressCountry;
  }

  /**
   * <p>Getter for the field <code>addressState</code>.</p>
   *
   * @return a {@link java.lang.String} object.
   */
  public String getAddressState() {
    return addressState;
  }

  /**
   * <p>Setter for the field <code>addressState</code>.</p>
   *
   * @param addressState a {@link java.lang.String} object.
   */
  public void setAddressState(String addressState) {
    this.addressState = addressState;
  }

  /**
   * <p>Getter for the field <code>addressCity</code>.</p>
   *
   * @return a {@link java.lang.String} object.
   */
  public String getAddressCity() {
    return addressCity;
  }

  /**
   * <p>Setter for the field <code>addressCity</code>.</p>
   *
   * @param addressCity a {@link java.lang.String} object.
   */
  public void setAddressCity(String addressCity) {
    this.addressCity = addressCity;
  }

  /**
   * <p>Getter for the field <code>address</code>.</p>
   *
   * @return a {@link java.lang.String} object.
   */
  public String getAddress() {
    return address;
  }

  /**
   * <p>Setter for the field <code>address</code>.</p>
   *
   * @param address a {@link java.lang.String} object.
   */
  public void setAddress(String address) {
    this.address = address;
  }

  /**
   * <p>Getter for the field <code>phone</code>.</p>
   *
   * @return a {@link java.lang.String} object.
   */
  public String getPhone() {
    return phone;
  }

  /**
   * <p>Setter for the field <code>phone</code>.</p>
   *
   * @param phone a {@link java.lang.String} object.
   */
  public void setPhone(String phone) {
    this.phone = phone;
  }

  /**
   * <p>Getter for the field <code>email</code>.</p>
   *
   * @return a {@link java.lang.String} object.
   */
  public String getEmail() {
    return email;
  }

  /**
   * <p>Setter for the field <code>email</code>.</p>
   *
   * @param email a {@link java.lang.String} object.
   */
  public void setEmail(String email) {
    this.email = email;
  }

  /**
   * <p>Getter for the field <code>zip</code>.</p>
   *
   * @return a {@link java.lang.String} object.
   */
  public String getZip() {
    return zip;
  }

  /**
   * <p>Setter for the field <code>zip</code>.</p>
   *
   * @param zip a {@link java.lang.String} object.
   */
  public void setZip(String zip) {
    this.zip = zip;
  }
}
