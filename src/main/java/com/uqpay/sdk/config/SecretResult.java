package com.uqpay.sdk.config;

import com.uqpay.sdk.utils.enums.SignTypeEnum;

import java.io.Serializable;

public class SecretResult implements Serializable {
  private static final long serialVersionUID = 2200223224173942068L;
  private SignTypeEnum signType;
  private String signature;

  public SignTypeEnum getSignType() {
    return signType;
  }

  public void setSignType(SignTypeEnum signType) {
    this.signType = signType;
  }

  public String getSignature() {
    return signature;
  }

  public void setSignature(String signature) {
    this.signature = signature;
  }
}
