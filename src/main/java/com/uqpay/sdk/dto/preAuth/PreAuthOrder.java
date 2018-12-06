package com.uqpay.sdk.dto.preAuth;

import com.uqpay.sdk.dto.ParamExtendLink;
import com.uqpay.sdk.dto.ParamLink;
import com.uqpay.sdk.dto.common.MerchantHostDTO;
import com.uqpay.sdk.dto.common.OrderDTO;
import com.uqpay.sdk.dto.common.ServerHostDTO;
import com.uqpay.sdk.utils.Constants;

public class PreAuthOrder extends OrderDTO {
  private static final long serialVersionUID = -2263809333833674816L;

  @ParamLink(Constants.ORDER_TRANS_NAME)
  private String transName; // product info

  @ParamLink(Constants.RESULT_UQPAY_ORDER_ID)
  private long uqOrderId;

  @ParamExtendLink
  private ServerHostDTO serverHost;

  @ParamExtendLink
  private MerchantHostDTO merchantHost;

  public ServerHostDTO getServerHost() {
    return serverHost;
  }

  public void setServerHost(ServerHostDTO serverHost) {
    this.serverHost = serverHost;
  }

  public MerchantHostDTO getMerchantHost() {
    return merchantHost;
  }

  public void setMerchantHost(MerchantHostDTO merchantHost) {
    this.merchantHost = merchantHost;
  }

  public long getUqOrderId() {
    return uqOrderId;
  }

  public void setUqOrderId(long uqOrderId) {
    this.uqOrderId = uqOrderId;
  }

  public String getTransName() {
    return transName;
  }

  public void setTransName(String transName) {
    this.transName = transName;
  }
}
