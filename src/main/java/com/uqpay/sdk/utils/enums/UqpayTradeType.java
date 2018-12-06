package com.uqpay.sdk.utils.enums;

import com.uqpay.sdk.utils.HasValue;

public enum UqpayTradeType implements HasValue {
  pay((short) 101),
  cancel((short) 102),
  refund((short) 103),
  preauth((short)104),
  preauthcomplete((short)105),
  preauthcancel((short)106),
  preauthcc((short)107),
  verifycode((short)130),
  enroll((short) 131),
  withdraw((short) 140),
  query((short) 180);


  private short value;

  UqpayTradeType(short value) {
    this.value = value;
  }

  public short getValue() {
    return value;
  }

  public static UqpayTradeType valueOf(short value) {
    for (UqpayTradeType val: UqpayTradeType.values()) {
      if (value == val.getValue()) {
        return val;
      }
    }
    return null;
  }

}
