package com.uqpay.sdk.config;

import org.apache.commons.lang3.StringUtils;
import com.uqpay.sdk.exception.UqpayRSAException;
import com.uqpay.sdk.utils.RSAUtil;

import java.io.Serializable;
import java.security.PrivateKey;
import java.security.PublicKey;

public class RSAConfig implements Serializable {
  private static final long serialVersionUID = -7014284669389490818L;

  /**
   * RSA Private Key
   * the content of the private pem.
   * Tips: remove the comment line (which start with ----) and the line break.
   */
  private String privateKeyStr;
  /**
   * The pem file path of RSA Private Key
   * Tips: make sure you have the permission to read.
   */
  private String privateKeyPath;

  private String publicKeyStr;

  private String publicKeyPath;

  private PrivateKey privateKey;

  private PublicKey publicKey;

  public PrivateKey getPrivateKey() throws UqpayRSAException {
    if (privateKey != null) {
      return privateKey;
    }
    if (StringUtils.isNotEmpty(this.privateKeyPath)){
        privateKey = RSAUtil.loadPrivateKey(this.privateKeyPath, true);
    } else if (StringUtils.isNotEmpty(this.privateKeyStr)) {
      privateKey = RSAUtil.loadPrivateKey(this.privateKeyStr, false);
    }
    if (privateKey == null) {
      throw new UqpayRSAException("Load Uqpay Payment Private Key Fail!");
    }
    return privateKey;
  }

  public PublicKey getPublicKey() throws UqpayRSAException {
    if (publicKey != null) {
      return publicKey;
    }
    if (StringUtils.isNotEmpty(this.publicKeyPath)) {
      publicKey = RSAUtil.loadPublicKey(this.publicKeyPath, true);
    } else if (StringUtils.isNotEmpty(this.publicKeyStr)) {
      publicKey = RSAUtil.loadPublicKey(this.publicKeyStr, false);
    }
    if (publicKey == null) {
      throw new UqpayRSAException("Load Uqpay Payment Public Key Fail!");
    }
    return publicKey;
  }

  public String getPublicKeyStr() {
    return publicKeyStr;
  }

  public void setPublicKeyStr(String publicKeyStr) {
    this.publicKeyStr = publicKeyStr;
  }

  public String getPublicKeyPath() {
    return publicKeyPath;
  }

  public void setPublicKeyPath(String publicKeyPath) {
    this.publicKeyPath = publicKeyPath;
  }

  public String getPrivateKeyStr() {
    return privateKeyStr;
  }

  public void setPrivateKeyStr(String privateKeyStr) {
    this.privateKeyStr = privateKeyStr;
  }

  public String getPrivateKeyPath() {
    return privateKeyPath;
  }

  public void setPrivateKeyPath(String privateKeyPath) {
    this.privateKeyPath = privateKeyPath;
  }
}
