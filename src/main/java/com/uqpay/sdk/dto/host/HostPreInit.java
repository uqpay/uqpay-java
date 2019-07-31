package com.uqpay.sdk.dto.host;

import com.uqpay.sdk.dto.common.BaseJsonRequestDTO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

public class HostPreInit extends BaseJsonRequestDTO {
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
