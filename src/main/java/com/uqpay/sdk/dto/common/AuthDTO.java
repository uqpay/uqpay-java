package com.uqpay.sdk.dto.common;

import com.uqpay.sdk.dto.PaygateParams;
import com.uqpay.sdk.dto.ParamLink;
import com.uqpay.sdk.utils.Constants;

import javax.validation.constraints.Positive;

public class AuthDTO implements PaygateParams {
  @ParamLink(Constants.AUTH_MERCHANT_ID)
  @Positive
  private int merchantId;

  @ParamLink(Constants.AUTH_AGENT_ID)
  private int agentId;

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
