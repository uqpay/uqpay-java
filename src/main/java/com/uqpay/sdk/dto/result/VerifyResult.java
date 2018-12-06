package com.uqpay.sdk.dto.result;

import com.uqpay.sdk.dto.ParamLink;
import com.uqpay.sdk.utils.Constants;

public class VerifyResult extends BaseResult {
  private static final long serialVersionUID = 3077029124521925838L;
  @ParamLink(Constants.RESULT_UQPAY_ORDER_ID)
  private long uqOrderId; // this order id generate by uqpay

  public long getUqOrderId() {
    return uqOrderId;
  }

  public void setUqOrderId(long uqOrderId) {
    this.uqOrderId = uqOrderId;
  }
}
