package com.uqpay.sdk.dto.host;

import com.uqpay.sdk.dto.ParamLink;
import com.uqpay.sdk.dto.PaygateParams;
import com.uqpay.sdk.utils.Constants;

import javax.validation.constraints.NotBlank;

public class HostPayDTO implements PaygateParams {
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
