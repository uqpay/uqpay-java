package com.uqpay.sdk.dto.common;

import com.uqpay.sdk.dto.ParamLink;
import com.uqpay.sdk.dto.PaygateParams;
import com.uqpay.sdk.utils.Constants;

public class ThreeDFinishDTO implements PaygateParams {
  private static final long serialVersionUID = -1202789069782074286L;
  @ParamLink(Constants.BANK_CARD_THREE_D_UQORDERID)
  private long uqOrderId; // get from the first pay request with 3D,
  @ParamLink(Constants.BANK_CARD_THREE_D_PARES)
  private String paResponse;

  public long getUqOrderId() {
    return uqOrderId;
  }

  public void setUqOrderId(long uqOrderId) {
    this.uqOrderId = uqOrderId;
  }

  public String getPaResponse() {
    return paResponse;
  }

  public void setPaResponse(String paResponse) {
    this.paResponse = paResponse;
  }
}
