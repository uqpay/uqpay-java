package com.uqpay.sdk.dto.emvco;

import com.uqpay.sdk.dto.common.BaseJsonRequestDTO;
import com.uqpay.sdk.utils.enums.QRCodeChannelTypeEnum;
import com.uqpay.sdk.utils.enums.QRCodeTypeEnum;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

public class EmvcoCreateDTO extends BaseJsonRequestDTO {
  private static final long serialVersionUID = -4784272588247646493L;
  @NotNull
  private QRCodeChannelTypeEnum type;
  private String name;
  @NotNull
  private QRCodeTypeEnum codeType;
  @NotBlank
  private String terminalId;
  private String shopName;
  @PositiveOrZero
  private double amount;
  @NotBlank
  private String city;

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

  public QRCodeTypeEnum getCodeType() {
    return codeType;
  }

  public void setCodeType(QRCodeTypeEnum codeType) {
    this.codeType = codeType;
  }

  public String getTerminalId() {
    return terminalId;
  }

  public void setTerminalId(String terminalId) {
    this.terminalId = terminalId;
  }

  public String getShopName() {
    return shopName;
  }

  public void setShopName(String shopName) {
    this.shopName = shopName;
  }

  public double getAmount() {
    return amount;
  }

  public void setAmount(double amount) {
    this.amount = amount;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }
}
