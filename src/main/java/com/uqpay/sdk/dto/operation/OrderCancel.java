package com.uqpay.sdk.dto.operation;

import com.uqpay.sdk.utils.enums.UqpayTransType;

/**
 * for moment is the same as order refund
 */
public class OrderCancel extends OrderRefund {
  private static final long serialVersionUID = 1745908006537736324L;

  public OrderCancel() {
    this.setTradeType(UqpayTransType.cancel);
  }
}
