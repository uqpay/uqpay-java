package com.uqpay.sdk.payment.bean.tx;

public class RealNameOnlineTX extends BasicTX {
  private static final long serialVersionUID = 2109426224105990126L;
  // the FirstName of your customer
  private String firstName;
  // the LastName of your customer
  private String lastName;
  // the unique identifier of your customer in your system
  private String customerId;

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

  public String getCustomerId() {
    return customerId;
  }

  public void setCustomerId(String customerId) {
    this.customerId = customerId;
  }
}
