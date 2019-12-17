package com.uqpay.sdk.bean;

import com.uqpay.sdk.utils.enums.SignTypeEnum;

import java.io.Serializable;
import java.util.Date;

/**
 * Interface basic data for version 2.x of UQPAY Server
 */
public class BasicRequest implements Serializable {

  protected Date date; // Time requested
  protected SignTypeEnum signType;
  protected String signature;

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

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
