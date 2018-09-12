package com.uqpay.sdk.dto.result;

import com.uqpay.sdk.dto.ParamLink;
import com.uqpay.sdk.utils.Constants;

import java.util.Map;

public class InAppResult extends TransResult {

  @ParamLink(Constants.RESULT_ACCEPT_CODE)
  private String acceptCode; // this code will be used by UQPAY Mobile Client SDK to generate the wallet app url scheme

  public InAppResult() {super();}

  public String getAcceptCode() {
    return acceptCode;
  }

  public void setAcceptCode(String acceptCode) {
    this.acceptCode = acceptCode;
  }
}
