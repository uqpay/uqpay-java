package com.uqpay.sdk.operation.bean;

import jakarta.validation.constraints.NotEmpty;

public class RealNameDTO extends BaseJsonRequestDTO {
  private static final long serialVersionUID = -1684901069850587924L;
  @NotEmpty
  private String name;
  @NotEmpty
  private String pan;
  @NotEmpty
  private String mobile;
  @NotEmpty
  private String idCard;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPan() {
    return pan;
  }

  public void setPan(String pan) {
    this.pan = pan;
  }

  public String getMobile() {
    return mobile;
  }

  public void setMobile(String mobile) {
    this.mobile = mobile;
  }

  public String getIdCard() {
    return idCard;
  }

  public void setIdCard(String idCard) {
    this.idCard = idCard;
  }
}
