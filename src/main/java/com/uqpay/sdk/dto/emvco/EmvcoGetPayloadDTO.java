package com.uqpay.sdk.dto.emvco;

import com.uqpay.sdk.dto.common.BaseJsonRequestDTO;
import com.uqpay.sdk.utils.enums.QRCodeChannelTypeEnum;
import javax.validation.constraints.NotNull;

public class EmvcoGetPayloadDTO extends BaseJsonRequestDTO {
  private static final long serialVersionUID = 8526142489499905217L;
  @NotNull
  private QRCodeChannelTypeEnum type;

  public QRCodeChannelTypeEnum getType() {
    return type;
  }

  public void setType(QRCodeChannelTypeEnum type) {
    this.type = type;
  }
}
