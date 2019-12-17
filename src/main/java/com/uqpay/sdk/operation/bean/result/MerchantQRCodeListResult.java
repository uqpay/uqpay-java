package com.uqpay.sdk.operation.bean.result;

import java.util.List;

public class MerchantQRCodeListResult extends PageInfo {
  private static final long serialVersionUID = -8495111882408785897L;
  private List<QRCodeInfo> qrcList;

  public List<QRCodeInfo> getQrcList() {
    return qrcList;
  }

  public void setQrcList(List<QRCodeInfo> qrcList) {
    this.qrcList = qrcList;
  }
}
