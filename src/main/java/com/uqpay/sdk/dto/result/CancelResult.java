package com.uqpay.sdk.dto.result;

import java.util.Map;

/**
 * <p>CancelResult class.</p>
 *
 * @author zhengwei
 * @version $Id: $Id
 */
public class CancelResult extends RefundResult {
  private static final long serialVersionUID = 5851309052172045984L;

  /**
   * <p>Constructor for CancelResult.</p>
   *
   * @param mapResult a {@link java.util.Map} object.
   */
  public CancelResult(Map<String, Object> mapResult) {
    super(mapResult);
  }
}
