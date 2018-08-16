package com.uqpay.sdk.dto.result;

import com.uqpay.sdk.dto.ParamLink;
import com.uqpay.sdk.dto.PaygateParams;
import com.uqpay.sdk.utils.Constants;

public class BaseResult implements PaygateParams {
  private static final long serialVersionUID = -8488514523263655350L;
  @ParamLink(Constants.RESULT_CODE)
  private int code;
  @ParamLink(Constants.RESULT_MESSAGE)
  private String message;

  public int getCode() {
    return code;
  }

  public void setCode(int code) {
    this.code = code;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
