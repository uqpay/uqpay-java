package com.uqpay.sdk.config;

import com.uqpay.sdk.utils.enums.SignTypeEnum;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import com.uqpay.sdk.exception.UqpayRSAException;
import com.uqpay.sdk.utils.RSAUtil;

import java.io.Serializable;
import java.security.PublicKey;

/**
 * <p>SecureConfig class.</p>
 *
 * @author zhengwei
 * @version $Id: $Id
 */
public class SecureConfig implements Serializable {
  private static final long serialVersionUID = -7014284669389490818L;

  /**
   * Encipher the request data with Merchant/Partner private key (MD5 Salt) before send to UQPAY Service
   */
  private SecretKey encipher;

  /**
   * Decipher the response data from UQPAY Service with UQPAY Public RSA key
   */
  private SecretKey decipher;

  public SecretResult sign(String target) throws UqpayRSAException {
    if (encipher == null || !encipher.verify()) {
      throw new UqpayRSAException("Please setting your encipher secret key");
    }
    SecretResult result = new SecretResult();
    result.setSignType(encipher.getSignType());
    if (encipher.getSignType().equals(SignTypeEnum.RSA)) {
      if (StringUtils.isNotBlank(encipher.getContent())) {
        result.setSignature(RSAUtil.sign(target, RSAUtil.loadPrivateKey(encipher.getContent(), false)));
      } else {
        result.setSignature(RSAUtil.sign(target, RSAUtil.loadPrivateKey(encipher.getPath(), true)));
      }
    } else {
      String fc = target;
      if (StringUtils.isNotBlank(encipher.getContent())) {
        fc += encipher.getContent();
      } else {
        fc += target + RSAUtil.readKeyContentFrom(encipher.getPath(), true);
      }
      result.setSignature(DigestUtils.md5Hex(fc));
    }
    return result;
  }

  public boolean verify(String target, String signature) throws UqpayRSAException {
    if (decipher == null || !decipher.verify()) {
      throw new UqpayRSAException("Please setting your decipher secret key");
    }
    if (decipher.getSignType().equals(SignTypeEnum.RSA)) {
      PublicKey publicKey = StringUtils.isNotBlank(decipher.getContent()) ?
          RSAUtil.loadPublicKey(decipher.getContent(), false) : RSAUtil.loadPublicKey(decipher.getPath(), true);
      return RSAUtil.verify(target, signature, publicKey);
    }
    // TODO for this moment the UQPAY Service Only use RSA to encipher the data
    return false;
  }

  public SecretKey getEncipher() {
    return encipher;
  }

  public void setEncipher(SecretKey encipher) {
    this.encipher = encipher;
  }

  public SecretKey getDecipher() {
    return decipher;
  }

  public void setDecipher(SecretKey decipher) {
    this.decipher = decipher;
  }
}
