package com.uqpay.sdk.dto.result.appgate;

import com.uqpay.sdk.dto.PaygateParams;
import com.uqpay.sdk.utils.enums.SignTypeEnum;

import java.util.Calendar;
import java.util.Date;

public class BaseAppgateResult implements PaygateParams {
  private static final long serialVersionUID = 1215854296653798414L;
  private int merchantId;
  private int agentId;
  private String date;  // Unix Timestamp
  private SignTypeEnum signType;
  private String signature;
  private int respCode;
  private String respMessage;

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

  public Date getDate() {
    Calendar calendar = Calendar.getInstance();
    calendar.setTimeInMillis(Long.valueOf(date));
    return calendar.getTime();
  }

  public void setDate(String date) {
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

  public int getRespCode() {
    return respCode;
  }

  public void setRespCode(int respCode) {
    this.respCode = respCode;
  }

  public String getRespMessage() {
    return respMessage;
  }

  public void setRespMessage(String respMessage) {
    this.respMessage = respMessage;
  }
}
