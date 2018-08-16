package com.uqpay.sdk.utils;

/**
 * <p>PaymentSupportClient class.</p>
 *
 * @author zhengwei
 * @version $Id: $Id
 */
public enum PaymentSupportClient {

  PC_WEB((short)1),
  IOS((short)2),
  Android((short)3);

  private final Short value;

  PaymentSupportClient(Short value) {
    this.value = value;
  }

  /**
   * <p>Getter for the field <code>value</code>.</p>
   *
   * @return a {@link java.lang.Short} object.
   */
  public Short getValue() {
    return value;
  }

  /**
   * <p>fromShort.</p>
   *
   * @param value a short.
   * @return a {@link com.uqpay.sdk.utils.PaymentSupportClient} object.
   */
  public static PaymentSupportClient fromShort(short value) {
    for (PaymentSupportClient val : PaymentSupportClient.values()) {
      if (value == val.getValue()) {
        return val;
      }
    }
    return null;
  }
}
