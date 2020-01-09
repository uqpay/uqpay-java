package com.uqpay.sdk.payment.bean.result;

import java.util.Map;

public class OnlineResult extends BasePayResult {
  private static final long serialVersionUID = 3761106381766111812L;
  private String targetUrl;
  private String method; // http request method
  private Map<String,String> params;

  public String getTargetUrl() {
    return targetUrl;
  }

  public void setTargetUrl(String targetUrl) {
    this.targetUrl = targetUrl;
  }

  public String getMethod() {
    return method;
  }

  public void setMethod(String method) {
    this.method = method;
  }

  public Map<String, String> getParams() {
    return params;
  }

  public void setParams(Map<String, String> params) {
    this.params = params;
  }
}
