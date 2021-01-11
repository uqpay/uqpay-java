package com.uqpay.sdk.utils;

import com.uqpay.sdk.exception.UqpayRSAException;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.*;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * <p>RSAUtil class.</p>
 *
 * @author zhengwei
 */
public class RSAUtil {
  private static final String KEY_FACTORY_ALGORITHM = "RSA";
  private static final String SIGN_SHA1_WITH_RSA = "SHA1WithRSA";
  private static final String SIGN_CONTENT_CHARSET = "UTF-8";


  public static String readKeyContentFrom(String filePath, boolean isSalt) throws UqpayRSAException {
    try {
      InputStream in;
      if (filePath.startsWith("classpath:")) {
        in = RSAUtil.class.getResourceAsStream("/" + filePath.replaceAll("classpath:",""));
      } else {
        File file = new File(filePath);
        in = new FileInputStream(file);
      }
      BufferedReader br = new BufferedReader(new InputStreamReader(in));
      String readLine = null;
      StringBuilder sb = new StringBuilder();
      while ((readLine = br.readLine()) != null) {
        if (!isSalt && readLine.charAt(0) == '-') {
          continue;
        } else {
          sb.append(readLine);
        }
      }
      return sb.toString();
    } catch (IOException e) {
      throw new UqpayRSAException("Key Content Read Fail");
    }
  }

  /**
   * load private key
   *
   * @param privateKey a {@link java.lang.String} object.
   * @param isPath a boolean.
   * @throws com.uqpay.sdk.exception.UqpayRSAException
   * @return a {@link java.security.PrivateKey} object.
   */
  public static PrivateKey loadPrivateKey(String privateKey, boolean isPath) throws UqpayRSAException {
    try {
      String privateKeyStr;
      if (isPath) {
        privateKeyStr = readKeyContentFrom(privateKey, false);
      } else {
        privateKeyStr = privateKey.replaceAll("--(.*)--", "").replace("\n", "");
      }
      byte[] buffer = buildPKCS8Key(privateKeyStr);
      PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(buffer);
      KeyFactory keyFactory = KeyFactory.getInstance(KEY_FACTORY_ALGORITHM);
      return keyFactory.generatePrivate(keySpec);
    } catch (NoSuchAlgorithmException e) {
      throw new UqpayRSAException("No Such Algorithm");
    } catch (InvalidKeySpecException e) {
      throw new UqpayRSAException("Invalid Key Spec");
    } catch (NullPointerException e) {
      throw new UqpayRSAException("Key is empty");
    }
  }

  /**
   * load public key
   *
   * @param publicKey a {@link java.lang.String} object.
   * @param isPath a boolean.
   * @throws com.uqpay.sdk.exception.UqpayRSAException
   * @return a {@link java.security.PublicKey} object.
   */
  public static PublicKey loadPublicKey(String publicKey, boolean isPath) throws UqpayRSAException {
    try {
      String publicKeyStr;
      if (isPath) {
        publicKeyStr = readKeyContentFrom(publicKey, false);
      } else {
        publicKeyStr = publicKey.replaceAll("--(.*)--", "").replace("\n", "");
      }
      byte[] buffer = Base64.decodeBase64(publicKeyStr);
      KeyFactory keyFactory = KeyFactory.getInstance(KEY_FACTORY_ALGORITHM);
      X509EncodedKeySpec keySpec = new X509EncodedKeySpec(buffer);
      return keyFactory.generatePublic(keySpec);
    } catch (NoSuchAlgorithmException e) {
      throw new UqpayRSAException("No Such Algorithm");
    } catch (InvalidKeySpecException e) {
      throw new UqpayRSAException("Invalid Key Spec");
    } catch (NullPointerException e) {
      throw new UqpayRSAException("Key is empty");
    }
  }

  /**
   * Build PKCS8 Key From RSA Private Key
   *
   * @param privateKey
   * @return
   * @throws IOException
   */
  private static byte[] buildPKCS8Key(String privateKey) {
    final byte[] innerKey = Base64.decodeBase64(privateKey);
    final byte[] result = new byte[innerKey.length + 26];
    System.arraycopy(Base64.decodeBase64("MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKY="), 0, result, 0, 26);
    System.arraycopy(BigInteger.valueOf(result.length - 4).toByteArray(), 0, result, 2, 2);
    System.arraycopy(BigInteger.valueOf(innerKey.length).toByteArray(), 0, result, 24, 2);
    System.arraycopy(innerKey, 0, result, 26, innerKey.length);
    return result;
  }

  /**
   * <p>sign.</p>
   *
   * @param content a {@link java.lang.String} object.
   * @param privateKey a {@link java.security.PrivateKey} object.
   * @return a {@link java.lang.String} object.
   * @throws com.uqpay.sdk.exception.UqpayRSAException if any.
   */
  public static String sign(String content, PrivateKey privateKey) throws UqpayRSAException {
    if (privateKey == null) {
      throw new UqpayRSAException("Key is empty");
    }
    try {
      Signature signature = Signature.getInstance(SIGN_SHA1_WITH_RSA);
      signature.initSign(privateKey);
      signature.update(content.getBytes(SIGN_CONTENT_CHARSET));
      return Base64.encodeBase64String(signature.sign());
    } catch (NoSuchAlgorithmException e) {
      throw new UqpayRSAException("No Such Algorithm");
    } catch (InvalidKeyException e) {
      throw new UqpayRSAException("Invalid Key");
    } catch (UnsupportedEncodingException e) {
      throw new UqpayRSAException("unsupported encoding exception");
    } catch (SignatureException e) {
      throw new UqpayRSAException("signature exception");
    } catch (NullPointerException e) {
      throw new UqpayRSAException("Key is empty");
    }
  }

  /**
   * <p>verify.</p>
   *
   * @param content a {@link java.lang.String} object.
   * @param sign a {@link java.lang.String} object.
   * @param publicKey a {@link java.security.PublicKey} object.
   * @return a boolean.
   * @throws com.uqpay.sdk.exception.UqpayRSAException if any.
   */
  public static boolean verify(String content, String sign, PublicKey publicKey) throws UqpayRSAException {
    try {
      Signature signature = Signature.getInstance(SIGN_SHA1_WITH_RSA);
      signature.initVerify(publicKey);
      signature.update(content.getBytes(SIGN_CONTENT_CHARSET));
      return signature.verify(Base64.decodeBase64(sign));
    } catch (NoSuchAlgorithmException e) {
      throw new UqpayRSAException("No Such Algorithm");
    } catch (InvalidKeyException e) {
      throw new UqpayRSAException("Invalid Key");
    } catch (UnsupportedEncodingException e) {
      throw new UqpayRSAException("unsupported encoding exception");
    } catch (SignatureException e) {
      throw new UqpayRSAException("signature exception");
    } catch (NullPointerException e) {
      throw new UqpayRSAException("Key is empty");
    }
  }

  public static String encrypt(String content, PublicKey publicKey) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException, BadPaddingException, IllegalBlockSizeException {
    Cipher cipher = Cipher.getInstance(KEY_FACTORY_ALGORITHM);
    cipher.init(Cipher.ENCRYPT_MODE, publicKey);
    return Base64.encodeBase64String(cipher.doFinal(content.getBytes(StandardCharsets.UTF_8)));
  }

}
