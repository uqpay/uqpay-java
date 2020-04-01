package com.uqpay.sdk.payment.bean.result;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.uqpay.sdk.utils.enums.UqpayScanType;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(
    ignoreUnknown = true
)
public class DigiccyResult extends QRCodeResult {
  private static final long serialVersionUID = 4803576611307457372L;
  private String bip21;
  private String address;
  private String bip70;

  public String getBip21() {
    return bip21;
  }

  public void setBip21(String bip21) {
    this.bip21 = bip21;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getBip70() {
    return bip70;
  }

  public void setBip70(String bip70) {
    this.bip70 = bip70;
  }
}
