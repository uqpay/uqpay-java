package com.uqpay.sdk.exception;

import com.uqpay.sdk.utils.Constants;

import java.util.Map;

/**
 * <p>UqpayPayFailException class.</p>
 *
 * @author zhengwei
 * @version $Id: $Id
 */
public class UqpayPayFailException extends UqpayResultVerifyException{
  private int code;
  /**
   * <p>Constructor for UqpayPayFailException.</p>
   *
   * @param msg a {@link java.lang.String} object.
   * @param resultMap a {@link java.util.Map} object.
   */
  public UqpayPayFailException(String msg, Map<String, Object> resultMap) {
    super(msg, resultMap);
    this.code = Integer.valueOf(resultMap.get(Constants.RESULT_CODE).toString());
  }

  /**
   * <p>Getter for the field <code>code</code>.</p>
   *
   * @return a int.
   */
  public int getCode() {
    return code;
  }
}
