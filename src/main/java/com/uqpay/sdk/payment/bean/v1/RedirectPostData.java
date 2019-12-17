package com.uqpay.sdk.payment.bean.v1;

import com.uqpay.sdk.payment.bean.v1.PaygateParams;

import java.util.Map;

public class RedirectPostData implements PaygateParams {
  private static final long serialVersionUID = 5208406779771278758L;
  private String apiURL;
  Map<String, String> postData;

  public String getApiURL() {
    return apiURL;
  }

  public void setApiURL(String apiURL) {
    this.apiURL = apiURL;
  }

  public Map<String, String> getPostData() {
    return postData;
  }

  public void setPostData(Map<String, String> postData) {
    this.postData = postData;
  }
}
