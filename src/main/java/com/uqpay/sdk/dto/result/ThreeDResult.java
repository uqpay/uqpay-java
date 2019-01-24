package com.uqpay.sdk.dto.result;

import com.uqpay.sdk.dto.ParamLink;
import com.uqpay.sdk.dto.PaygateParams;
import com.uqpay.sdk.utils.Constants;
/**
 * these results valued when 3D CreditCard payment
 * use {@link paRequest} as the post body, send to {@link ascUrl} from client
 */
public class ThreeDResult implements PaygateParams {
  private static final long serialVersionUID = 7007131443663615391L;
  @ParamLink(Constants.RESULT_3D_PA_REQUEST)
  private String paRequest;
  @ParamLink(Constants.RESULT_3D_ASC_URL)
  private String ascUrl;

  public String getPaRequest() {
    return paRequest;
  }

  public void setPaRequest(String paRequest) {
    this.paRequest = paRequest;
  }

  public String getAscUrl() {
    return ascUrl;
  }

  public void setAscUrl(String ascUrl) {
    this.ascUrl = ascUrl;
  }
}
