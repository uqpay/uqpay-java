package com.uqpay.sdk.utils;

import com.uqpay.sdk.config.SecretResult;
import com.uqpay.sdk.config.SecureConfig;
import com.uqpay.sdk.dto.ParamLink;
import com.uqpay.sdk.dto.PaygateParams;
import com.uqpay.sdk.dto.common.BaseJsonRequestDTO;
import com.uqpay.sdk.dto.result.BaseResult;
import com.uqpay.sdk.exception.UqpayPayFailException;
import okhttp3.*;
import com.uqpay.sdk.exception.UqpayRSAException;
import com.uqpay.sdk.exception.UqpayResultVerifyException;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PayUtil {
  public static OkHttpClient httpClient = new OkHttpClient.Builder().connectTimeout(60, TimeUnit.SECONDS).build();

  public static Map<String, String> params2Map(PaygateParams... params) {
    Map<String, String> paramsMap = new HashMap<>();
    Arrays.asList(params).forEach(paygateParams -> {
      if (paygateParams != null) {
        params2Map(paramsMap, paygateParams);
      }
    });
    return paramsMap;
  }

  public static void params2Map(Map<String, String> target, PaygateParams params) {
    Field[] fields = Tools.getAllFields(params.getClass());
    for (int i = 0; i < fields.length; i++) {
      Field field = fields[i];
      ParamLink paramLink = field.getAnnotation(ParamLink.class);
      if (paramLink == null) continue;
      String name = Tools.capitalize(field.getName());
      Object realValue = null;
      try {
        Method getValue = params.getClass().getMethod("get" + name);
        Object value = getValue.invoke(params);
        String targetType = paramLink.targetType();
        if (value instanceof HasValue) {
          realValue = ((HasValue) value).getValue();
        } else if (field.getType().isEnum()) {
          realValue = ((Enum) value).name();
        } else if (!targetType.equals("")) {
          switch (paramLink.targetType()) {
            case "JSON":
              realValue = Tools.mapToJson((Map) value);
              break;
            default:
              realValue = value;
          }
        } else {
          switch (field.getType().getName()) {
            case "java.lang.Integer":
            case "int":
            case "java.lang.Double":
            case "double":
            case "java.lang.Long":
            case "long":
              if (!value.toString().equals("0")) {
                realValue = value;
              }
              break;
            case "java.util.Date":
              realValue = ((Date) value).getTime();
              break;
            case "java.util.Currency":
              realValue = ((Currency) value).getCurrencyCode();
              break;
            default:
              realValue = value;
          }
        }
      } catch (Exception ex) {
        // no need care no method exception
      }
      if (realValue != null) {
        String valueKey = paramLink.value();
        target.put(valueKey, realValue.toString());
      }
    }
  }

  public static <T> T map2Params(Class<T> clazz, Map<String, String> source) {
    Field[] fields = Tools.getAllFields(clazz);
    try {
      T target = clazz.newInstance();
      for (int i = 0; i < fields.length; i++) {
        Field field = fields[i];
        ParamLink paramLink = field.getAnnotation(ParamLink.class);
        if (paramLink == null) continue;
        Object value = source.get(paramLink.value());
        if (value == null || value.equals("")) continue;
        String name = Tools.capitalize(field.getName());
        Method setValue = clazz.getMethod("set" + name, field.getType());
        Object realValue;
        String targetType = paramLink.targetType();
        if (!targetType.equals("")) {
          switch (targetType) {
            case "JSON":
              realValue = Tools.json2map(value.toString());
              break;
            default:
              realValue = value;
          }
        } else if (field.getType().isEnum()) {
          if (field.getType().getInterfaces().length > 0 && field.getType().getInterfaces()[0].equals(HasValue.class)) {
            realValue = Tools.enumValueOf(field.getType(), Short.valueOf(value.toString()));
          } else {
            realValue = Tools.enumValueOf(field.getType(), String.valueOf(value));
          }
        } else {
          switch (field.getType().getName()) {
            case "java.lang.Integer":
            case "int":
              realValue = Integer.valueOf(value.toString());
              break;
            case "java.lang.Double":
            case "double":
              realValue = Double.valueOf(value.toString());
              break;
            case "java.lang.Long":
            case "long":
              realValue = Long.valueOf(value.toString());
              break;
            case "java.util.Date":
              Calendar calendar = Calendar.getInstance();
              calendar.setTimeInMillis(Long.valueOf(value.toString()));
              realValue = calendar.getTime();
              break;
            case "java.util.Currency":
              realValue = Currency.getInstance(value.toString());
              break;
            default:
              realValue = value;
          }
        }
        setValue.invoke(target, realValue);
      }
      return target;
    } catch (Exception ex) {
      return null;
    }
  }

  public static Map<String, String> signParams(Map<String, String> paramsMap, SecureConfig config)
      throws UnsupportedEncodingException, UqpayRSAException {
    String paramsQuery = Tools.stringify(paramsMap, false, Constants.AUTH_SIGN_TYPE);
    SecretResult secret = config.sign(paramsQuery);
    paramsMap.put(Constants.AUTH_SIGN, secret.getSignature());
    paramsMap.put(Constants.AUTH_SIGN_TYPE, secret.getSignType().name());
    return paramsMap;
  }

  public static void signParams(BaseJsonRequestDTO params, SecureConfig config) throws IOException, UqpayRSAException {
    params.setSignType(config.getEncipher().getSignType());
    SecretResult secret = config.sign(Tools.objToJson(params));
    params.setSignature(secret.getSignature());
    params.setSignType(secret.getSignType());
  }

  public static void verifyUqpayNotice(Map<String, String> paramsMap, SecureConfig config)
      throws UnsupportedEncodingException, UqpayRSAException, UqpayResultVerifyException {
    if (paramsMap.get(Constants.AUTH_SIGN) == null)
      throw new UqpayResultVerifyException("The payment result is not a valid uqpay result, signature is missing", paramsMap);
    Map<String, String> needVerifyParams = new HashMap<>();
    paramsMap.forEach((key, value) -> {
      if (!key.equals(Constants.AUTH_SIGN) && !key.equals(Constants.AUTH_SIGN_TYPE)) {
        needVerifyParams.put(key, value.toString());
      }
    });
    String paramsQuery = Tools.stringify(needVerifyParams, false);
    if (!config.verify(paramsQuery, paramsMap.get(Constants.AUTH_SIGN)))
      throw new UqpayResultVerifyException("The payment result is invalid, be sure is from the UQPAY server", paramsMap);
  }

  public static void verifyUqpayNotice(String jsonString, String signature, SecureConfig secure) throws UqpayResultVerifyException, UqpayRSAException {
    if (signature == null || signature.equals("")) throw new UqpayResultVerifyException("The result is not a valid uqpay result, signature is missing", jsonString);
    String verifyString = jsonString.replace(signature, "000000");
    if (!secure.verify(verifyString, signature))
      throw new UqpayResultVerifyException("The result is invalid, be sure is from the UQPAY server", jsonString);
  }

  public static Map<String, String> generatePayParamsMap(SecureConfig config, PaygateParams... params) throws UnsupportedEncodingException, UqpayRSAException {
    Map<String, String> paramsMap = PayUtil.params2Map(params);
    return PayUtil.signParams(paramsMap, config);
  }

  public static Request generateFormRequest(Map<String, String> paramsMap, String url) {
    FormBody.Builder formBody = new FormBody.Builder();
    paramsMap.forEach((key, value) -> formBody.add(key, value));
    Request request = new Request.Builder()
        .url(url)
        .post(formBody.build())
        .build();
    return request;
  }

  public static Request generateJsonRequest(Object bodyObject, String url) throws IOException {
    String requestBody = Tools.objToJson(bodyObject);
    MediaType mediaType = MediaType.parse("application/json;charset=UTF-8");
    Request request = new Request.Builder()
        .url(url)
        .post(RequestBody.create(mediaType, requestBody))
        .build();
    return request;
  }

  private static void doIfRequestFailed(Response response) throws UqpayPayFailException, IOException {
    Map<String, String> resultMap = Tools.json2map(response.body().string());
    BaseResult result = PayUtil.map2Params(BaseResult.class, resultMap);
    throw new UqpayPayFailException(result.getCode(), result.getMessage());
  }

  public static Response doSyncRequest(Request request) throws IOException, UqpayPayFailException {
    Response response = httpClient.newCall(request).execute();
    if (response.isSuccessful()) {
      return response;
    }
    doIfRequestFailed(response);
    return response;
  }

  public static byte[] doFileDownloadRequest(Request request, String destPath) throws IOException, UqpayPayFailException {
    OkHttpClient client = new OkHttpClient.Builder().connectTimeout(10, TimeUnit.MINUTES).build();
    Response response = client.newCall(request).execute();
    if (response.isSuccessful()) {
      if (destPath == null || destPath.length() == 0) {
        return response.body().bytes();
      } else {
        // write the file
        InputStream inputStream = null;
        byte[] buf = new byte[2048];
        int len = 0;
        FileOutputStream fos = null;
        try {
          String contentDisposition = response.header("Content-Disposition");
          String fileName = "checking.zip";
          if (contentDisposition != null && contentDisposition.length() > 0 && contentDisposition.indexOf("filename=") > 0) {
            Matcher matcher =  Pattern.compile("filename=\"(.*?)\"").matcher(contentDisposition);
            if (matcher.find()) {
              fileName = matcher.group(1);
            }
          }
          inputStream = response.body().byteStream();
          File file = new File(destPath, fileName);
          fos = new FileOutputStream(file);
          while ((len = inputStream.read(buf)) != -1) {
            fos.write(buf, 0 , len);
          }
          fos.flush();
        } finally {
          if (inputStream != null) {
            inputStream.close();
          }
          if (fos != null) {
            fos.close();
          }
        }
        return null;
      }
    }
    doIfRequestFailed(response);
    return null;
  }
}
