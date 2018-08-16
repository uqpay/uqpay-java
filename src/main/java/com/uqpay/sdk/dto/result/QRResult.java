package com.uqpay.sdk.dto.result;

import com.uqpay.sdk.utils.Constants;
import com.uqpay.sdk.utils.UqpayScanType;

import java.util.Map;

/**
 * <p>QRResult class.</p>
 *
 * @author zhengwei
 * @version $Id: $Id
 */
public class QRResult extends TransResult {
  private static final long serialVersionUID = 1766958755757874110L;
  private UqpayScanType scanType;
  private String qrCodeUrl;
  private String qrCode;

  /**
   * <p>Constructor for QRResult.</p>
   */
  public QRResult() {super();}
  /**
   * <p>Constructor for QRResult.</p>
   *
   * @param mapResult a {@link java.util.Map} object.
   */
  public QRResult(Map<String, Object> mapResult) {
    super(mapResult);
    this.scanType = UqpayScanType.fromValue(Short.valueOf(mapResult.get(Constants.PAY_OPTIONS_SCAN_TYPE).toString()));
    if (this.scanType != null && this.scanType.equals(UqpayScanType.Consumer)) {
      this.qrCodeUrl = (String) mapResult.get(Constants.RESULT_QR_CODE_URL);
      this.qrCode = (String) mapResult.get(Constants.RESULT_QR_CODE_DATA);
    }
  }

  /**
   * <p>Getter for the field <code>scanType</code>.</p>
   *
   * @return a {@link com.uqpay.sdk.utils.UqpayScanType} object.
   */
  public UqpayScanType getScanType() {
    return scanType;
  }

  /**
   * <p>Setter for the field <code>scanType</code>.</p>
   *
   * @param scanType a {@link com.uqpay.sdk.utils.UqpayScanType} object.
   */
  public void setScanType(UqpayScanType scanType) {
    this.scanType = scanType;
  }

  /**
   * <p>Getter for the field <code>qrCodeUrl</code>.</p>
   *
   * @return a {@link java.lang.String} object.
   */
  public String getQrCodeUrl() {
    return qrCodeUrl;
  }

  /**
   * <p>Setter for the field <code>qrCodeUrl</code>.</p>
   *
   * @param qrCodeUrl a {@link java.lang.String} object.
   */
  public void setQrCodeUrl(String qrCodeUrl) {
    this.qrCodeUrl = qrCodeUrl;
  }

  /**
   * <p>Getter for the field <code>qrCode</code>.</p>
   *
   * @return a {@link java.lang.String} object.
   */
  public String getQrCode() {
    return qrCode;
  }

  /**
   * <p>Setter for the field <code>qrCode</code>.</p>
   *
   * @param qrCode a {@link java.lang.String} object.
   */
  public void setQrCode(String qrCode) {
    this.qrCode = qrCode;
  }
}
