package com.uqpay.sdk.utils;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import com.uqpay.sdk.config.CashierConfig;
import com.uqpay.sdk.config.MerchantConfig;
import com.uqpay.sdk.config.PaygateConfig;
import com.uqpay.sdk.dto.operation.OrderCancel;
import com.uqpay.sdk.dto.operation.OrderQuery;
import com.uqpay.sdk.dto.operation.OrderRefund;
import com.uqpay.sdk.dto.payment.CreditCard;
import com.uqpay.sdk.dto.payment.PayData;
import com.uqpay.sdk.exception.UqpayRSAException;
import com.uqpay.sdk.exception.UqpayResultVerifyException;
import com.uqpay.sdk.vo.UqpayCashier;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class PayUtil {
  public static OkHttpClient httpClient = new OkHttpClient.Builder().connectTimeout(60, TimeUnit.SECONDS).build();

  public static Map<String, String> generateDefPayParams(PayData payData, MerchantConfig config) {
    Map<String, String> paramsMap = new HashMap<>();
    paramsMap.put(Constants.AUTH_MERCHANT_ID, String.valueOf(config.getId()));
    paramsMap.put(Constants.ORDER_ID, payData.getOrderId());
    paramsMap.put(Constants.ORDER_AMOUNT, String.valueOf(payData.getAmount()));
    paramsMap.put(Constants.ORDER_CURRENCY, payData.getCurrency());
    paramsMap.put(Constants.ORDER_TRANS_NAME, payData.getTransName());
    paramsMap.put(Constants.ORDER_DATE, String.valueOf(payData.getDate().getTime()));
    if (payData.getQuantity() != 0) {
      paramsMap.put(Constants.ORDER_QUANTITY, String.valueOf(payData.getQuantity()));
    }
    if (payData.getStoreId() != 0) {
      paramsMap.put(Constants.ORDER_STORE_ID, String.valueOf(payData.getStoreId()));
    }
    if (payData.getSeller() != 0) {
      paramsMap.put(Constants.ORDER_SELLER, String.valueOf(payData.getSeller()));
    }
    if (payData.getChannelInfo() != null && !payData.getChannelInfo().equals("")) {
      paramsMap.put(Constants.ORDER_CHANNEL_INFO, payData.getChannelInfo());
    }
    if (payData.getExtendInfo() != null && !payData.getExtendInfo().equals("")) {
      paramsMap.put(Constants.ORDER_EXTEND_INFO, payData.getExtendInfo());
    }
    paramsMap.put(Constants.PAY_OPTIONS_METHOD_ID, String.valueOf(payData.getMethodId()));
    paramsMap.put(Constants.PAY_OPTIONS_TRADE_TYPE, payData.getTradeType().name());
    paramsMap.put(Constants.PAY_OPTIONS_ASYNC_NOTICE_URL, payData.getCallbackUrl());
    return paramsMap;
  }

  public static Map<String, String> generateCreditCardPayParams(CreditCard creditCard) {
    Map<String, String> paramsMap = new HashMap<>();
    paramsMap.put(Constants.CREDIT_CARD_FIRST_NAME, creditCard.getFirstName());
    paramsMap.put(Constants.CREDIT_CARD_LAST_NAME, creditCard.getLastName());
    paramsMap.put(Constants.CREDIT_CARD_CARD_NUM, creditCard.getCardNum());
    paramsMap.put(Constants.CREDIT_CARD_CVV, creditCard.getCvv());
    paramsMap.put(Constants.CREDIT_CARD_EXPIRE_MONTH, creditCard.getExpireMonth());
    paramsMap.put(Constants.CREDIT_CARD_EXPIRE_YEAR, creditCard.getExpireYear());
    paramsMap.put(Constants.CREDIT_CARD_ADDRESS_COUNTRY, creditCard.getAddressCountry());
    paramsMap.put(Constants.CREDIT_CARD_ADDRESS_STATE, creditCard.getAddressState());
    paramsMap.put(Constants.CREDIT_CARD_ADDRESS_CITY, creditCard.getAddressCity());
    paramsMap.put(Constants.CREDIT_CARD_ADDRESS, creditCard.getAddress());
    paramsMap.put(Constants.CREDIT_CARD_PHONE, creditCard.getPhone());
    paramsMap.put(Constants.CREDIT_CARD_EMAIL, creditCard.getEmail());
    paramsMap.put(Constants.CREDIT_CARD_ZIP, creditCard.getZip());
    return paramsMap;
  }

  public static Map<String, String> generateRefundParams(OrderRefund refund, MerchantConfig config) {
    Map<String, String> paramsMap = new HashMap<>();
    paramsMap.put(Constants.AUTH_MERCHANT_ID, String.valueOf(config.getId()));
    paramsMap.put(Constants.ORDER_ID, refund.getOrderId());
    paramsMap.put(Constants.ORDER_AMOUNT, String.valueOf(refund.getAmount()));
    paramsMap.put(Constants.ORDER_DATE, String.valueOf(refund.getDate().getTime()));
    if (refund.getExtendInfo() != null && !refund.getExtendInfo().equals("")) {
      paramsMap.put(Constants.ORDER_EXTEND_INFO, refund.getExtendInfo());
    }
    paramsMap.put(Constants.PAY_OPTIONS_TRADE_TYPE, refund.getTradeType().name());
    paramsMap.put(Constants.PAY_OPTIONS_ASYNC_NOTICE_URL, refund.getCallbackUrl());
    return paramsMap;
  }

  public static Map<String, String> generateCancelParams(OrderCancel cancel, MerchantConfig config) {
    Map<String, String> paramsMap = new HashMap<>();
    paramsMap.put(Constants.AUTH_MERCHANT_ID, String.valueOf(config.getId()));
    paramsMap.put(Constants.ORDER_ID, cancel.getOrderId());
    paramsMap.put(Constants.ORDER_DATE, String.valueOf(cancel.getDate().getTime()));
    if (cancel.getExtendInfo() != null && !cancel.getExtendInfo().equals("")) {
      paramsMap.put(Constants.ORDER_EXTEND_INFO, cancel.getExtendInfo());
    }
    paramsMap.put(Constants.PAY_OPTIONS_TRADE_TYPE, cancel.getTradeType().name());
    return paramsMap;
  }

  public static Map<String, String> generateQueryParams(OrderQuery query, MerchantConfig config) {
    Map<String, String> paramsMap = new HashMap<>();
    paramsMap.put(Constants.AUTH_MERCHANT_ID, String.valueOf(config.getId()));
    paramsMap.put(Constants.ORDER_ID, query.getOrderId());
    paramsMap.put(Constants.ORDER_DATE, String.valueOf(query.getDate().getTime()));
    paramsMap.put(Constants.PAY_OPTIONS_TRADE_TYPE, query.getTradeType().name());
    return paramsMap;
  }

  public static String generateCashierLink(UqpayCashier cashier, CashierConfig config)
      throws UnsupportedEncodingException, UqpayRSAException {
    Map<String, String> paramsMap = new HashMap<>();
    paramsMap.putAll(cashier.getParamsMap());
    String paramsQuery = Tools.stringify(paramsMap, false);
    String sign = RSAUtil.sign(paramsQuery, config.getRSA().getPrivateKey());
    paramsMap.put(Constants.AUTH_SIGN, sign);
    return config.getApiRoot() + "?" + Tools.stringify(paramsMap, true);
  }

  public static Map<String, String> signParams(Map<String, String> paramsMap, PaygateConfig config)
      throws UnsupportedEncodingException, UqpayRSAException {
    String paramsQuery = Tools.stringify(paramsMap, false);
    String sign = RSAUtil.sign(paramsQuery, config.getRSA().getPrivateKey());
    paramsMap.put(Constants.AUTH_SIGN, sign);
    return paramsMap;
  }

  public static void verifyUqpayNotice(Map<String, Object> paramsMap, PaygateConfig config)
      throws UnsupportedEncodingException, UqpayRSAException, UqpayResultVerifyException {
    if (paramsMap.get(Constants.AUTH_SIGN) == null)
      throw new UqpayResultVerifyException("The payment result is not a valid uqpay result, sign data is missing", paramsMap);
    Map<String, String> needVerifyParams = new HashMap<>();
    paramsMap.forEach((key, value) -> {
      if (key != Constants.AUTH_SIGN) {
        needVerifyParams.put(key, value.toString());
      }
    });
    String paramsQuery = Tools.stringify(needVerifyParams, false);
    boolean verify = RSAUtil.verify(paramsQuery, paramsMap.get(Constants.AUTH_SIGN).toString(), config.getUqpayPublicKey().getPublicKey());
    if (!verify) throw new UqpayResultVerifyException("The payment result is invalid, be sure is from the UQPAY server", paramsMap);
  }

  public static Request generateRequest(Map<String, String> paramsMap, String url) {
    FormBody.Builder formBody = new FormBody.Builder();
    paramsMap.forEach((key, value) -> formBody.add(key, value));
    Request request = new Request.Builder()
        .url(url)
        .post(formBody.build())
        .build();
    return request;
  }

  public static Response doSyncFormRequest(Request request) throws IOException {
    Response response = httpClient.newCall(request).execute();
    if (response.isSuccessful()) {
      return response;
    }
    throw new IOException("request uqpay service fail: " + response);
  }
}
