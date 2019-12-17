package com.uqpay.sdk.hostui.bean;

import com.uqpay.sdk.operation.bean.BaseJsonRequestDTO;

import javax.validation.constraints.NotBlank;

public class HostUIInit extends BaseJsonRequestDTO {
  private static final long serialVersionUID = 6060167973109641075L;
  @NotBlank
  private String customer;

  public String getCustomer() {
    return customer;
  }

  public void setCustomer(String customer) {
    this.customer = customer;
  }
}
