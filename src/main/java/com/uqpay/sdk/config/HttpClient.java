package com.uqpay.sdk.config;

import com.uqpay.sdk.exception.UqpayPayFailException;

import java.util.Map;

/**
 * Implement this interface, and set it to the UQPay,
 * UQPAY library will use this to request api
 */
public interface HttpClient {
  /**
   * request uqpay server
   * @param headers http headers, key is the attr name, value is the attr value
   * @param requestBody request body, JSON string or Form Data
   * @param url api address
   * @return response body, when the response http status is 20x, return the response body
   */
  public String post(Map<String, String> headers, String requestBody, String url) throws UqpayPayFailException;

  /**
   * download resources from uqpay server
   *
   * @param headers http headers, key is the attr name, value is the attr value
   * @param jsonBody request body, format by JSON
   * @param url api address
   * @param resourceType type of resource
   */
  public void download(Map<String, String> headers, String jsonBody, String url, ResourceTypeEnum resourceType);
}
