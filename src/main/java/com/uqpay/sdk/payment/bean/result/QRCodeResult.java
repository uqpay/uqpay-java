package com.uqpay.sdk.payment.bean.result;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.uqpay.sdk.utils.enums.UqpayScanType;

import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(
    ignoreUnknown = true
)
public class QRCodeResult extends BasePayResult {
  private static final long serialVersionUID = 3307873145177256248L;
  private short scanType;
  private String qrCodeUrl;
  private String qrCode;

  public UqpayScanType getScanType() {
    return UqpayScanType.valueOf(scanType);
  }

  public void setScanType(short scanType) {
    this.scanType = scanType;
  }

  public String getQrCodeUrl() {
    return qrCodeUrl;
  }

  public void setQrCodeUrl(String qrCodeUrl) {
    this.qrCodeUrl = qrCodeUrl;
  }

  public String getQrCode() {
    return qrCode;
  }

  public void setQrCode(String qrCode) {
    this.qrCode = qrCode;
  }
}
