package com.uqpay.sdk.dto.result;

import com.uqpay.sdk.utils.Constants;

import java.util.Map;

public class InAppResult extends TransResult {

  private String acceptCode; // this code will be used by UQPAY Mobile Client SDK to generate the wallet app url scheme

  public InAppResult() {super();}
  public InAppResult(Map<String, Object> mapResult) {
    super(mapResult);
    this.acceptCode = mapResult.get(Constants.RESULT_ACCEPT_CODE).toString();
  }

  public String getAcceptCode() {
    return acceptCode;
  }

  public void setAcceptCode(String acceptCode) {
    this.acceptCode = acceptCode;
  }
}
