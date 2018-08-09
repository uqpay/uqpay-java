package com.uqpay.sdk.dto.result;

import java.util.Map;

public class CancelResult extends RefundResult {
  private static final long serialVersionUID = 5851309052172045984L;

  public CancelResult(Map<String, Object> mapResult) {
    super(mapResult);
  }
}
