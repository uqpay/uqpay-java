package com.uqpay.sdk.dto.result;

import com.uqpay.sdk.utils.Constants;

import java.util.Map;

/**
 * <p>InAppResult class.</p>
 *
 * @author zhengwei
 * @version $Id: $Id
 */
public class InAppResult extends TransResult {

  private String acceptCode; // this code will be used by UQPAY Mobile Client SDK to generate the wallet app url scheme

  /**
   * <p>Constructor for InAppResult.</p>
   */
  public InAppResult() {super();}
  /**
   * <p>Constructor for InAppResult.</p>
   *
   * @param mapResult a {@link java.util.Map} object.
   */
  public InAppResult(Map<String, Object> mapResult) {
    super(mapResult);
    this.acceptCode = mapResult.get(Constants.RESULT_ACCEPT_CODE).toString();
  }

  /**
   * <p>Getter for the field <code>acceptCode</code>.</p>
   *
   * @return a {@link java.lang.String} object.
   */
  public String getAcceptCode() {
    return acceptCode;
  }

  /**
   * <p>Setter for the field <code>acceptCode</code>.</p>
   *
   * @param acceptCode a {@link java.lang.String} object.
   */
  public void setAcceptCode(String acceptCode) {
    this.acceptCode = acceptCode;
  }
}
