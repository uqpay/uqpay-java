package com.uqpay.sdk.dto.common;

import com.uqpay.sdk.dto.ParamLink;
import com.uqpay.sdk.dto.PaygateParams;
import com.uqpay.sdk.utils.Constants;

import javax.validation.constraints.NotBlank;

public class ServerHostDTO implements PaygateParams {
  private static final long serialVersionUID = -6436473040294692672L;
  @ParamLink(Constants.SERVER_HOST_CARD_TOKEN)
  @NotBlank
  private String token; // after enroll the card, you will get this token from uqpay
  @ParamLink(Constants.BANK_CARD_PHONE)
  @NotBlank
  private String phone;

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }
}
