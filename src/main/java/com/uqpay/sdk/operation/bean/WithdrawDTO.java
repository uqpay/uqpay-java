package com.uqpay.sdk.operation.bean;

import com.uqpay.sdk.operation.bean.BaseJsonRequestDTO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

public class WithdrawDTO extends BaseJsonRequestDTO {
  private static final long serialVersionUID = -7986849254750772386L;
  @Positive
  private Double amount;
  @NotBlank
  private String currency;

  private String notes;

  public Double getAmount() {
    return amount;
  }

  public void setAmount(Double amount) {
    this.amount = amount;
  }

  public String getCurrency() {
    return currency;
  }

  public void setCurrency(String currency) {
    this.currency = currency;
  }

  public String getNotes() {
    return notes;
  }

  public void setNotes(String notes) {
    this.notes = notes;
  }
}
