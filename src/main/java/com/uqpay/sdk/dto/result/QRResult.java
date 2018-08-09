package com.uqpay.sdk.dto.result;

import com.uqpay.sdk.utils.Constants;
import com.uqpay.sdk.utils.UqpayScanType;

import java.util.Map;

public class QRResult extends TransResult {
  private static final long serialVersionUID = 1766958755757874110L;
  private UqpayScanType scanType;
  private String qrCodeUrl;
  private String qrCode;

  public QRResult() {super();}
  public QRResult(Map<String, Object> mapResult) {
    super(mapResult);
    this.scanType = UqpayScanType.fromValue(Short.valueOf(mapResult.get(Constants.PAY_OPTIONS_SCAN_TYPE).toString()));
    if (this.scanType != null && this.scanType.equals(UqpayScanType.Consumer)) {
      this.qrCodeUrl = (String) mapResult.get(Constants.RESULT_QR_CODE_URL);
      this.qrCode = (String) mapResult.get(Constants.RESULT_QR_CODE_DATA);
    }
  }

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
