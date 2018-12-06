package com.uqpay.sdk.dto.common;

import com.uqpay.sdk.utils.enums.SignTypeEnum;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class BaseJsonRequestDTO extends AuthDTO {
  private static final long serialVersionUID = 6434237925369884603L;
  @NotNull
  private SignTypeEnum signType = SignTypeEnum.RSA;
  @NotBlank
  private Date date;

  private String signature;

  public BaseJsonRequestDTO() {
    this.signature = "000000";  // Don't valued this by yourself, the SDK will automatic sign base on your config
  }

  public SignTypeEnum getSignType() {
    return signType;
  }

  public void setSignType(SignTypeEnum signType) {
    this.signType = signType;
  }

  public String getSignature() {
    return signature;
  }

  public void setSignature(String signature) {
    this.signature = signature;
  }

  public String getDate() {
    return String.valueOf(date.getTime());
  }

  public void setDate(Date date) {
    this.date = date;
  }
}
