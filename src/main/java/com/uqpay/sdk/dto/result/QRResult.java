package com.uqpay.sdk.dto.result;

import com.uqpay.sdk.dto.ParamLink;
import com.uqpay.sdk.utils.Constants;
import com.uqpay.sdk.utils.UqpayScanType;

public class QRResult extends TransResult {
  private static final long serialVersionUID = 1766958755757874110L;

  @ParamLink(Constants.PAY_OPTIONS_SCAN_TYPE)
  private UqpayScanType scanType;
  @ParamLink(Constants.RESULT_QR_CODE_URL)
  private String qrCodeUrl;
  @ParamLink(Constants.RESULT_QR_CODE_DATA)
  private String qrCode;

  public QRResult() {super();}

  public UqpayScanType getScanType() {
    return scanType;
  }

  public void setScanType(UqpayScanType scanType) {
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
