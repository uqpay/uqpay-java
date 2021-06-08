package com.uqpay.sdk.hostui.bean;

import com.uqpay.sdk.payment.bean.v1.ParamLink;
import com.uqpay.sdk.payment.bean.v1.PaygateParams;
import com.uqpay.sdk.utils.Constants;

import jakarta.validation.constraints.NotBlank;

public class HostUICard implements PaygateParams {
  private static final long serialVersionUID = -1816261143164358885L;
  @ParamLink(Constants.HOSTED_CARDTOKEN)
  @NotBlank
  private String cardToken; // you can get this token from client sdk

  public String getCardToken() {
    return cardToken;
  }

  public void setCardToken(String cardToken) {
    this.cardToken = cardToken;
  }
}
