package com.uqpay.sdk;

import com.uqpay.sdk.bean.ApiResponse;
import com.uqpay.sdk.bean.BasicRequest;
import com.uqpay.sdk.config.*;
import com.uqpay.sdk.operation.bean.BaseJsonRequestDTO;
import com.uqpay.sdk.operation.bean.result.BaseAppgateResult;
import com.uqpay.sdk.exception.UqpayRSAException;
import com.uqpay.sdk.exception.UqpayResultVerifyException;
import com.uqpay.sdk.utils.Constants;
import com.uqpay.sdk.utils.PayUtil;
import com.uqpay.sdk.utils.Tools;
import com.uqpay.sdk.utils.enums.SignTypeEnum;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class UQPay {

  /**
   * for Merchant this is the merchant ID
   * for Agent this is the agent ID
   */
  private int memberId;

  private MemberTypeEnum memberType;

  private EnvEnum env = EnvEnum.TEST;

  private SecureConfig secureConfig;

  private HttpClient httpClient;

  private String[] paygate = {"http://localhost:8686", "https://paygate.uqpay.net", "https://paygate.uqpay.com"};
  private String[] appgate = {"https://appgate.uqpay.cn", "https://appgate.uqpay.net", "https://appgate.uqpay.com"};
  private String[] cashier = {"https://cashier.uqpay.cn", "https://cashier.uqpay.net", "https://cashier.uqpay.com"};

  public static String IDENTITY_HEADER_KEY_MEMBER = "uq-member-id";
  public static String IDENTITY_HEADER_KEY_TYPE = "uq-member-type";
  public static final String SIGNATURE_PLACEHOLDER = "000000";

  public UQPay(int memberId, MemberTypeEnum memberType) {
    this.memberId = memberId;
    this.memberType = memberType;
    secureConfig = new SecureConfig();
  }

  public static UQPay setting(MemberTypeEnum memberType, int memberId) {
    return new UQPay(memberId, memberType);
  }

  public UQPay env(EnvEnum env) {
    this.env = env;
    return this;
  }

  public UQPay encipher(SignTypeEnum signType, String key, boolean isPath) {
    secureConfig.setEncipher(createKey(signType, key, isPath));
    return this;
  }

  public UQPay decipher(SignTypeEnum signType, String key, boolean isPath) {
    secureConfig.setDecipher(createKey(signType, key, isPath));
    return this;
  }

  public UQPay httpClient(Class<? extends HttpClient> clientClass) {
    try {
      httpClient = clientClass.newInstance();
    } catch (Exception ignore) {
    }
    return this;
  }

  private SecretKey createKey(SignTypeEnum signType, String key, boolean isPath) {
    SecretKey secretKey = new SecretKey();
    secretKey.setSignType(signType);
    if (isPath) {
      secretKey.setPath(key);
    } else {
      secretKey.setContent(key);
    }
    return secretKey;
  }

  /**
   * v1.x pay api request
   */
  @Deprecated
  public <T> T request(Map<String, String> paramsMap, String url, Class<T> resultClass) throws UqpayRSAException, IOException, UqpayResultVerifyException {
    // set identity info
    if (memberType.equals(MemberTypeEnum.AGENT)) {
      paramsMap.put(Constants.AUTH_AGENT_ID, String.valueOf(memberId));
    }
    if (memberType.equals(MemberTypeEnum.MERCHANT)) {
      paramsMap.put(Constants.AUTH_MERCHANT_ID, String.valueOf(memberId));
    }
    // sign
    String paramsQuery = Tools.stringify(paramsMap, false, Constants.AUTH_SIGN_TYPE);
    SecretResult secretResult = secureConfig.sign(paramsQuery);
    paramsMap.put(Constants.AUTH_SIGN, secretResult.getSignature());
    paramsMap.put(Constants.AUTH_SIGN_TYPE, secretResult.getSignType().name());
    // request
    Map<String, String> headers = new HashMap<>();
    headers.put("content-type", "application/x-www-form-urlencoded;charset=UTF-8");
    String rspBody = httpClient.post(headers, Tools.stringify(paramsMap, true), url);
    if (rspBody == null) {
      return null;
    }
    // verify
    Map<String, String> resultMap = Tools.json2map(rspBody);
    PayUtil.verifyUqpayNotice(resultMap, secureConfig);

    // return target result
    return PayUtil.map2Params(resultClass, resultMap);
  }

  @Deprecated
  private <T extends BaseJsonRequestDTO> T wrapParams(T params) throws IOException, UqpayRSAException {
    // set identity info
    if (memberType.equals(MemberTypeEnum.AGENT)) {
      params.setAgentId(memberId);
    }
    if (memberType.equals(MemberTypeEnum.MERCHANT)) {
      params.setMerchantId(memberId);
    }

    // sign
    params.setSignType(secureConfig.getEncipher().getSignType());
    SecretResult secretResult = secureConfig.sign(Tools.objToJson(params));
    params.setSignType(secretResult.getSignType());
    params.setSignature(secretResult.getSignature());
    return params;
  }

  /**
   * v1.x operation api request
   */
  @Deprecated
  public <T extends BaseAppgateResult, E extends BaseJsonRequestDTO> T request(E params, String url, Class<T> resultClass) throws IOException, UqpayRSAException, UqpayResultVerifyException {
    params.setDate(new Date());
    wrapParams(params);
    // request
    Map<String, String> headers = new HashMap<>();
    headers.put("content-type", "application/json;charset=UTF-8");
    headers.put("accept", "application/json;charset=UTF-8");
    String rspBody = httpClient.post(headers, Tools.objToJson(params), url);

    // verify
    T result = Tools.json2Obj(rspBody, resultClass);
    PayUtil.verifyUqpayNotice(rspBody, result.getSignature(), secureConfig);
    // return target result
    return result;
  }

  public <E extends BasicRequest, T> ApiResponse<T> request(E params, String url, Class<T> resultClass) throws IOException, UqpayRSAException, UqpayResultVerifyException {
    params.setDate(new Date());
    // set identity info
    Map<String, String> headers = new HashMap<>();
    headers.put(IDENTITY_HEADER_KEY_MEMBER, String.valueOf(memberId));
    headers.put(IDENTITY_HEADER_KEY_TYPE, String.valueOf(memberType));
    headers.put("content-type", "application/json;charset=UTF-8");
    headers.put("accept", "application/json;charset=UTF-8");

    // sign
    params.setSignType(secureConfig.getEncipher().getSignType());
    params.setSignature(SIGNATURE_PLACEHOLDER);
    SecretResult secretResult = secureConfig.sign(Tools.objToJson(params));
    params.setSignature(secretResult.getSignature());

    // request
    String rspBody = httpClient.post(headers, Tools.objToJson(params), url);
    if (rspBody == null) {
      return null;
    }
    ApiResponse<T> response = Tools.json2Rsp(rspBody, resultClass);

    // only when the request is success, need verify;
    if (response.isSuccess()) {
      // verify
      String verifyString = rspBody.replace(response.getSignature(), SIGNATURE_PLACEHOLDER);
      if (!secureConfig.verify(verifyString, response.getSignature()))
        throw new UqpayResultVerifyException("The result is invalid, be sure is from the UQPAY server", rspBody);
    }
    return response;
  }

  public int getMemberId() {
    return memberId;
  }

  public MemberTypeEnum getMemberType() {
    return memberType;
  }

  public EnvEnum getEnv() {
    return env;
  }

  public SecureConfig getSecureConfig() {
    return secureConfig;
  }

  public <T extends BaseJsonRequestDTO> void doDownload(T params, ResourceTypeEnum resourceType, String url) throws IOException, UqpayRSAException {
    wrapParams(params);
    Map<String, String> headers = new HashMap<>();
    headers.put("content-type", "application/json;charset=UTF-8");
    headers.put("accept", "application/json;charset=UTF-8");
    httpClient.download(headers, Tools.objToJson(params), url, resourceType);
  }

  public String getPayUrl(String subPath) {
    return paygate[env.getValue()] + subPath;
  }
  public String getAppUrl(String subPath) {
    return appgate[env.getValue()] + subPath;
  }
  public String getCashierUrl(String subPath) {
    return cashier[env.getValue()] + subPath;
  }
}
