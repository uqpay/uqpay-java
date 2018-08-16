package com.uqpay.sdk.dto.payment;

import com.uqpay.sdk.utils.PaymentSupportClient;
import com.uqpay.sdk.utils.UqpayScanType;
import com.uqpay.sdk.utils.UqpayTradeType;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * Pay Options Data Structure
 * Use for each payment API
 *
 * @author zhengwei
 * @version $Id: $Id
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

  /**
   * <p>Getter for the field <code>methodId</code>.</p>
   *
   * @return a int.
   */
  public int getMethodId() {
    return methodId;
  }

  /**
   * <p>Setter for the field <code>methodId</code>.</p>
   *
   * @param methodId a int.
   */
  public void setMethodId(int methodId) {
    this.methodId = methodId;
  }

  /**
   * <p>Getter for the field <code>tradeType</code>.</p>
   *
   * @return a {@link com.uqpay.sdk.utils.UqpayTradeType} object.
   */
  public UqpayTradeType getTradeType() {
    return tradeType;
  }

  /**
   * <p>Setter for the field <code>tradeType</code>.</p>
   *
   * @param tradeType a {@link com.uqpay.sdk.utils.UqpayTradeType} object.
   */
  public void setTradeType(UqpayTradeType tradeType) {
    this.tradeType = tradeType;
  }

  /**
   * <p>Getter for the field <code>scanType</code>.</p>
   *
   * @return a {@link com.uqpay.sdk.utils.UqpayScanType} object.
   */
  public UqpayScanType getScanType() {
    return scanType;
  }

  /**
   * <p>Setter for the field <code>scanType</code>.</p>
   *
   * @param scanType a {@link com.uqpay.sdk.utils.UqpayScanType} object.
   */
  public void setScanType(UqpayScanType scanType) {
    this.scanType = scanType;
  }

  /**
   * <p>Getter for the field <code>callbackUrl</code>.</p>
   *
   * @return a {@link java.lang.String} object.
   */
  public String getCallbackUrl() {
    return callbackUrl;
  }

  /**
   * <p>Setter for the field <code>callbackUrl</code>.</p>
   *
   * @param callbackUrl a {@link java.lang.String} object.
   */
  public void setCallbackUrl(String callbackUrl) {
    this.callbackUrl = callbackUrl;
  }

  /**
   * <p>Getter for the field <code>returnUrl</code>.</p>
   *
   * @return a {@link java.lang.String} object.
   */
  public String getReturnUrl() {
    return returnUrl;
  }

  /**
   * <p>Setter for the field <code>returnUrl</code>.</p>
   *
   * @param returnUrl a {@link java.lang.String} object.
   */
  public void setReturnUrl(String returnUrl) {
    this.returnUrl = returnUrl;
  }

  /**
   * <p>Getter for the field <code>identity</code>.</p>
   *
   * @return a {@link java.lang.String} object.
   */
  public String getIdentity() {
    return identity;
  }

  /**
   * <p>Setter for the field <code>identity</code>.</p>
   *
   * @param identity a {@link java.lang.String} object.
   */
  public void setIdentity(String identity) {
    this.identity = identity;
  }

  /**
   * <p>Getter for the field <code>client</code>.</p>
   *
   * @return a {@link com.uqpay.sdk.utils.PaymentSupportClient} object.
   */
  public PaymentSupportClient getClient() {
    return client;
  }

  /**
   * <p>Setter for the field <code>client</code>.</p>
   *
   * @param client a {@link com.uqpay.sdk.utils.PaymentSupportClient} object.
   */
  public void setClient(PaymentSupportClient client) {
    this.client = client;
  }
}
