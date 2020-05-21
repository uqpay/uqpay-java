package com.uqpay.sdk.payment.bean.tx;

import com.uqpay.sdk.utils.enums.UqpayScanType;

public class QRCodeTX extends BasicTX {
  private static final long serialVersionUID = -3442437526024402714L;
  private UqpayScanType scanType;

  private String identity;

  public UqpayScanType getScanType() {
    return scanType;
  }

  public void setScanType(UqpayScanType scanType) {
    this.scanType = scanType;
  }

  public String getIdentity() {
    return identity;
  }

  public void setIdentity(String identity) {
    this.identity = identity;
  }
}
