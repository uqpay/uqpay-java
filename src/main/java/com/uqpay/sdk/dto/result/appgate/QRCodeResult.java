package com.uqpay.sdk.dto.result.appgate;

import com.uqpay.sdk.utils.enums.QRCodeChannelTypeEnum;

public class QRCodeResult extends BaseAppgateResult {
  private static final long serialVersionUID = -7909223222694115856L;
  private int codeId;
  private QRCodeChannelTypeEnum type;
  private String name;
  private String payload;
  private String terminalId;
  private String content; // QR Code Content

  public int getCodeId() {
    return codeId;
  }

  public void setCodeId(int codeId) {
    this.codeId = codeId;
  }

  public QRCodeChannelTypeEnum getType() {
    return type;
  }

  public void setType(QRCodeChannelTypeEnum type) {
    this.type = type;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPayload() {
    return payload;
  }

  public void setPayload(String payload) {
    this.payload = payload;
  }

  public String getTerminalId() {
    return terminalId;
  }

  public void setTerminalId(String terminalId) {
    this.terminalId = terminalId;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }
}
