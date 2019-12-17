package com.uqpay.sdk.payment.bean.v1;

import com.uqpay.sdk.payment.bean.v1.ParamLink;
import com.uqpay.sdk.payment.bean.v1.PaygateParams;
import com.uqpay.sdk.utils.Constants;
import com.uqpay.sdk.utils.enums.UqpayTransType;

import java.util.Date;

public class BaseResult implements PaygateParams {
  private static final long serialVersionUID = 1089463092439412978L;
  @ParamLink(Constants.RESULT_CODE)
  private int code;
  @ParamLink(Constants.RESULT_MESSAGE)
  private String message;

  @ParamLink(Constants.AUTH_SIGN)
  private String sign;
  @ParamLink(Constants.AUTH_SIGN_TYPE)
  private String signType;

  @ParamLink(Constants.AUTH_MERCHANT_ID)
  private int merchantId;
  @ParamLink(Constants.AUTH_AGENT_ID)
  private int agentId;
  @ParamLink(Constants.PAY_OPTIONS_METHOD_ID)
  private int methodId;
  @ParamLink(Constants.PAY_OPTIONS_TRADE_TYPE)
  private UqpayTransType transType;
  @ParamLink(Constants.ORDER_DATE)
  private Date date;

  public String getSignType() {
    return signType;
  }

  public void setSignType(String signType) {
    this.signType = signType;
  }

  public String getSign() {
    return sign;
  }

  public void setSign(String sign) {
    this.sign = sign;
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

  public int getMethodId() {
    return methodId;
  }

  public void setMethodId(int methodId) {
    this.methodId = methodId;
  }

  public UqpayTransType getTransType() {
    return transType;
  }

  public void setTransType(UqpayTransType transType) {
    this.transType = transType;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public int getCode() {
    return code;
  }

  public void setCode(int code) {
    this.code = code;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
