package com.uqpay.sdk.utils;

/**
 * <p>UqpayScanType class.</p>
 *
 * @author zhengwei
 * @version $Id: $Id
 */
public enum UqpayScanType {
  Merchant((short) 0), // merchant scan consumer
  Consumer((short) 1); // consumer scan merchant

  private Short value;

  UqpayScanType(short value) { this.value = value; }
  /**
   * <p>Getter for the field <code>value</code>.</p>
   *
   * @return a {@link java.lang.Short} object.
   */
  public Short getValue() {
    return value;
  }

  /**
   * <p>fromValue.</p>
   *
   * @param value a short.
   * @return a {@link com.uqpay.sdk.utils.UqpayScanType} object.
   */
  public static UqpayScanType fromValue(short value) {
    for (UqpayScanType val: UqpayScanType.values()) {
      if (value == val.getValue()) {
        return val;
      }
    }
    return null;
  }
}
