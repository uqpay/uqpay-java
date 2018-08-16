package com.uqpay.sdk.config;

import org.apache.commons.lang3.StringUtils;
import com.uqpay.sdk.exception.UqpayRSAException;
import com.uqpay.sdk.utils.RSAUtil;

import java.io.Serializable;
import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * <p>RSAConfig class.</p>
 *
 * @author zhengwei
 * @version $Id: $Id
 */
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

  /**
   * <p>Getter for the field <code>privateKey</code>.</p>
   *
   * @return a {@link java.security.PrivateKey} object.
   * @throws com.uqpay.sdk.exception.UqpayRSAException if any.
   */
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

  /**
   * <p>Getter for the field <code>publicKey</code>.</p>
   *
   * @return a {@link java.security.PublicKey} object.
   * @throws com.uqpay.sdk.exception.UqpayRSAException if any.
   */
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

  /**
   * <p>Getter for the field <code>publicKeyStr</code>.</p>
   *
   * @return a {@link java.lang.String} object.
   */
  public String getPublicKeyStr() {
    return publicKeyStr;
  }

  /**
   * <p>Setter for the field <code>publicKeyStr</code>.</p>
   *
   * @param publicKeyStr a {@link java.lang.String} object.
   */
  public void setPublicKeyStr(String publicKeyStr) {
    this.publicKeyStr = publicKeyStr;
  }

  /**
   * <p>Getter for the field <code>publicKeyPath</code>.</p>
   *
   * @return a {@link java.lang.String} object.
   */
  public String getPublicKeyPath() {
    return publicKeyPath;
  }

  /**
   * <p>Setter for the field <code>publicKeyPath</code>.</p>
   *
   * @param publicKeyPath a {@link java.lang.String} object.
   */
  public void setPublicKeyPath(String publicKeyPath) {
    this.publicKeyPath = publicKeyPath;
  }

  /**
   * <p>Getter for the field <code>privateKeyStr</code>.</p>
   *
   * @return a {@link java.lang.String} object.
   */
  public String getPrivateKeyStr() {
    return privateKeyStr;
  }

  /**
   * <p>Setter for the field <code>privateKeyStr</code>.</p>
   *
   * @param privateKeyStr a {@link java.lang.String} object.
   */
  public void setPrivateKeyStr(String privateKeyStr) {
    this.privateKeyStr = privateKeyStr;
  }

  /**
   * <p>Getter for the field <code>privateKeyPath</code>.</p>
   *
   * @return a {@link java.lang.String} object.
   */
  public String getPrivateKeyPath() {
    return privateKeyPath;
  }

  /**
   * <p>Setter for the field <code>privateKeyPath</code>.</p>
   *
   * @param privateKeyPath a {@link java.lang.String} object.
   */
  public void setPrivateKeyPath(String privateKeyPath) {
    this.privateKeyPath = privateKeyPath;
  }
}
