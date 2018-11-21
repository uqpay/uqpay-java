package com.uqpay.sdk.dto.common;

import com.uqpay.sdk.dto.ParamLink;
import com.uqpay.sdk.dto.PaygateParams;
import com.uqpay.sdk.utils.Constants;

import javax.validation.constraints.NotEmpty;

public class BankCardHostBaseDTO implements PaygateParams {
  private static final long serialVersionUID = 6311888445858903438L;
  @ParamLink(Constants.SERVER_HOST_CARD_TOKEN)
  @NotEmpty
  private String token;

  public BankCardHostBaseDTO() {
  }

  public static BankCardHostBaseDTO valueOf(BankCardDTO bankCard) {
    BankCardHostBaseDTO result = new BankCardHostBaseDTO();
    result.setToken(bankCard.getToken());
    return result;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }
}
