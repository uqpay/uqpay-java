package com.uqpay.sdk.exception;

import java.util.Map;

/**
 * <p>UqpayResultVerifyException class.</p>
 *
 * @author zhengwei
 * @version $Id: $Id
 */
public class UqpayResultVerifyException extends Exception {
  private Map<String, Object> resultMap;
  /**
   * <p>Constructor for UqpayResultVerifyException.</p>
   */
  public UqpayResultVerifyException() {
    super();
  }
  /**
   * <p>Constructor for UqpayResultVerifyException.</p>
   *
   * @param msg a {@link java.lang.String} object.
   * @param resultMap a {@link java.util.Map} object.
   */
  public UqpayResultVerifyException(String msg, Map<String, Object> resultMap) {
    super(msg);
    this.resultMap = resultMap;
  }

  /**
   * <p>Getter for the field <code>resultMap</code>.</p>
   *
   * @return a {@link java.util.Map} object.
   */
  public Map<String, Object> getResultMap() {
    return resultMap;
  }
}
