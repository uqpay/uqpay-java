package com.uqpay.sdk.utils;

import com.uqpay.sdk.config.BaseConfig;
import com.uqpay.sdk.dto.ParamLink;
import com.uqpay.sdk.dto.PaygateParams;
import com.uqpay.sdk.dto.result.BaseResult;
import com.uqpay.sdk.exception.UqpayPayFailException;
import okhttp3.*;
import com.uqpay.sdk.config.PaygateConfig;
import com.uqpay.sdk.exception.UqpayRSAException;
import com.uqpay.sdk.exception.UqpayResultVerifyException;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class PayUtil {
  public static OkHttpClient httpClient = new OkHttpClient.Builder().connectTimeout(0, TimeUnit.SECONDS).build();

  public static Map<String, String> params2Map(PaygateParams... params) {
    Map<String, String> paramsMap = new HashMap<>();
    Arrays.asList(params).forEach(paygateParams -> {
      params2Map(paramsMap, paygateParams);
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

  public static Map<String, String> signParams(Map<String, String> paramsMap, PaygateConfig config)
      throws UnsupportedEncodingException, UqpayRSAException {
    String paramsQuery = Tools.stringify(paramsMap, false);
    String sign = RSAUtil.sign(paramsQuery, config.getRSA().getPrivateKey());
    paramsMap.put(Constants.AUTH_SIGN, sign);
    return paramsMap;
  }

  public static String signParams(Object params, BaseConfig config) throws IOException, UqpayRSAException {
    String jsonData = Tools.objToJson(params);
    String sign = RSAUtil.sign(jsonData, config.getRSA().getPrivateKey());
    return sign;
  }

  public static void verifyUqpayNotice(Map<String, String> paramsMap, PaygateConfig config)
      throws UnsupportedEncodingException, UqpayRSAException, UqpayResultVerifyException {
    if (paramsMap.get(Constants.AUTH_SIGN) == null)
      throw new UqpayResultVerifyException("The payment result is not a valid uqpay result, sign data is missing", paramsMap);
    Map<String, String> needVerifyParams = new HashMap<>();
    paramsMap.forEach((key, value) -> {
      if (!key.equals(Constants.AUTH_SIGN)) {
        needVerifyParams.put(key, value.toString());
      }
    });
    String paramsQuery = Tools.stringify(needVerifyParams, false);
    boolean verify = RSAUtil.verify(paramsQuery, paramsMap.get(Constants.AUTH_SIGN).toString(), config.getRSA().getPublicKey());
    if (!verify)
      throw new UqpayResultVerifyException("The payment result is invalid, be sure is from the UQPAY server", paramsMap);
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

  public static Response doSyncRequest(Request request) throws IOException, UqpayPayFailException {
    Response response = httpClient.newCall(request).execute();
    if (response.isSuccessful()) {
      return response;
    }
    Map<String, String> resultMap = Tools.json2map(response.body().string());
    BaseResult result = PayUtil.map2Params(BaseResult.class, resultMap);
    throw new UqpayPayFailException(result.getCode(), result.getMessage());
  }
}
