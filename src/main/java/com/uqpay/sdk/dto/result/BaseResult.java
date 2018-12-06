package com.uqpay.sdk.dto.result;

import com.uqpay.sdk.dto.ParamLink;
import com.uqpay.sdk.dto.PaygateParams;
import com.uqpay.sdk.utils.Constants;
import com.uqpay.sdk.utils.enums.UqpayTradeType;

import java.util.Date;

public class BaseResult implements PaygateParams {
  private static final long serialVersionUID = 6862743445457844915L;
  @ParamLink(Constants.RESULT_CODE)
  private int code;
  @ParamLink(Constants.RESULT_MESSAGE)
  private String message;

  @ParamLink(Constants.AUTH_SIGN)
  private String sign;
  @ParamLink(Constants.AUTH_MERCHANT_ID)
  private int merchantId;
  @ParamLink(Constants.AUTH_AGENT_ID)
  private int agentId;
  @ParamLink(Constants.PAY_OPTIONS_METHOD_ID)
  private int methodId;
  @ParamLink(Constants.PAY_OPTIONS_TRADE_TYPE)
  private UqpayTradeType tradeType;
  @ParamLink(Constants.ORDER_DATE)
  private Date date;

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

  public UqpayTradeType getTradeType() {
    return tradeType;
  }

  public void setTradeType(UqpayTradeType tradeType) {
    this.tradeType = tradeType;
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
