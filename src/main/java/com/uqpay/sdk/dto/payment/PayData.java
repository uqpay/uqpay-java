package com.uqpay.sdk.dto.payment;

import com.uqpay.sdk.utils.PaymentSupportClient;
import com.uqpay.sdk.utils.UqpayScanType;
import com.uqpay.sdk.utils.UqpayTradeType;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * Pay Options Data Structure
 * Use for each payment API
 */
public class PayData extends OrderData {
  private static final long serialVersionUID = -4629662413862582348L;

  /**
   * is required
   */
  @NotNull
  private Integer methodId;
  @NotNull
  private UqpayTradeType tradeType;
  @NotEmpty
  private String callbackUrl; // async notice url

  /**
   * not required for each payment API
   */
  private String returnUrl; // sync notice url
  private UqpayScanType scanType; // only required for qr code payment
  private String identity; // only required for qr code payment when scan type is Merchant
  private PaymentSupportClient client; // only required for in app payment

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

  public UqpayScanType getScanType() {
    return scanType;
  }

  public void setScanType(UqpayScanType scanType) {
    this.scanType = scanType;
  }

  public String getCallbackUrl() {
    return callbackUrl;
  }

  public void setCallbackUrl(String callbackUrl) {
    this.callbackUrl = callbackUrl;
  }

  public String getReturnUrl() {
    return returnUrl;
  }

  public void setReturnUrl(String returnUrl) {
    this.returnUrl = returnUrl;
  }

  public String getIdentity() {
    return identity;
  }

  public void setIdentity(String identity) {
    this.identity = identity;
  }

  public PaymentSupportClient getClient() {
    return client;
  }

  public void setClient(PaymentSupportClient client) {
    this.client = client;
  }
}
