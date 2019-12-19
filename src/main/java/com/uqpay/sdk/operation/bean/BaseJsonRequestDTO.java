package com.uqpay.sdk.operation.bean;

import com.uqpay.sdk.utils.enums.SignTypeEnum;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.io.Serializable;
import java.util.Date;

public class BaseJsonRequestDTO implements Serializable {
  private static final long serialVersionUID = 1544402511660776535L;
  private SignTypeEnum signType;
  private Date date;
  private String signature;

  @PositiveOrZero
  private int merchantId;

  @PositiveOrZero
  private int agentId;

  public BaseJsonRequestDTO() {
    this.signature = "000000";  // Don't valued this by yourself, the SDK will automatic sign base on your config
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

  public String getDate() {
    return String.valueOf(date.getTime());
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public int getMerchantId() {
    return merchantId;
  }

  public void setMerchantId(int merchantId) {
    this.merchantId = merchantId;
  }

  public int getAgentId() {
    return agentId;
  }

  public void setAgentId(int agentId) {
    this.agentId = agentId;
  }
}
