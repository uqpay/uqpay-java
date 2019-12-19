package com.uqpay.sdk.config;

import com.uqpay.sdk.utils.enums.SignTypeEnum;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

public class SecretKey implements Serializable {

  private SignTypeEnum signType = SignTypeEnum.RSA;
  /**
   * RSA Key Or MD5 Salt Content
   */
  private String content;
  /**
   * The pem file path of RSA Private Key
   * Or The txt file path of MD5 Salt
   * Tips: make sure you have the permission to read.
   */
  private String path;

  public boolean verify() {
    return signType != null && (StringUtils.isNotBlank(content) || StringUtils.isNotBlank(path));
  }

  public SignTypeEnum getSignType() {
    return signType;
  }

  public void setSignType(SignTypeEnum signType) {
    this.signType = signType;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }
}
