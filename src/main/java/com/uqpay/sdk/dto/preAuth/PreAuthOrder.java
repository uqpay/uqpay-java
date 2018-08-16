package com.uqpay.sdk.dto.preAuth;

import com.uqpay.sdk.dto.ParamLink;
import com.uqpay.sdk.dto.common.OrderDTO;
import com.uqpay.sdk.utils.Constants;

public class PreAuthOrder extends OrderDTO {
  private static final long serialVersionUID = -3389655181374284867L;

  @ParamLink(Constants.ORDER_TRANS_NAME)
  private String transName; // product info

  @ParamLink(Constants.RESULT_UQPAY_ORDER_ID)
  private long uqOrderId;

  public long getUqOrderId() {
    return uqOrderId;
  }

  public void setUqOrderId(long uqOrderId) {
    this.uqOrderId = uqOrderId;
  }

  public String getTransName() {
    return transName;
  }

  public void setTransName(String transName) {
    this.transName = transName;
  }
}
