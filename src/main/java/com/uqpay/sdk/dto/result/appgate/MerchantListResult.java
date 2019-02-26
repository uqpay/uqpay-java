package com.uqpay.sdk.dto.result.appgate;

import java.util.List;

public class MerchantListResult extends PageInfo {
  private static final long serialVersionUID = 9156199328263707604L;
  private List<MerchantInfo> merchantList;

  public List<MerchantInfo> getMerchantList() {
    return merchantList;
  }

  public void setMerchantList(List<MerchantInfo> merchantList) {
    this.merchantList = merchantList;
  }
}
