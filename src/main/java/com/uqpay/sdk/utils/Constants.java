package com.uqpay.sdk.utils;

public class Constants {
  public static final String AUTH_SIGN = "sign";
  public static final String AUTH_MERCHANT_ID = "merchantid";
  public static final String AUTH_AGENT_ID = "agentId";

  public static final String PAY_OPTIONS_TRADE_TYPE = "tradetype";
  public static final String PAY_OPTIONS_METHOD_ID = "methodid";
  public static final String PAY_OPTIONS_SCAN_TYPE = "scantype";
  public static final String PAY_OPTIONS_ASYNC_NOTICE_URL = "callbackurl";
  public static final String PAY_OPTIONS_SYNC_NOTICE_URL = "returnurl";
  public static final String PAY_OPTIONS_CLIENT_TYPE = "clienttype";
  public static final String PAY_OPTIONS_IDENTITY = "identity";
  public static final String PAY_OPTIONS_MERCHANT_CITY = "merchantcity";
  public static final String PAY_OPTIONS_TERMINAL_ID = "terminalid";
  public static final String PAY_OPTIONS_OPEN_ID = "openid";

  public static final String ORDER_ID = "orderid";
  public static final String ORDER_AMOUNT = "amount";
  public static final String ORDER_CURRENCY = "currency";
  public static final String ORDER_TRANS_NAME = "transname";
  public static final String ORDER_DATE = "date";
  public static final String ORDER_QUANTITY = "quantity";
  public static final String ORDER_STORE_ID = "storeid";
  public static final String ORDER_SELLER = "seller";
  public static final String ORDER_CHANNEL_INFO = "channelinfo";
  public static final String ORDER_EXTEND_INFO = "extendinfo";

  public static final String RESULT_MESSAGE = "message";
  public static final String RESULT_CODE = "code";
  public static final String RESULT_UQPAY_ORDER_ID = "uqorderid";
  public static final String RESULT_UQPAY_RELATED_ID = "relatedid";
  public static final String RESULT_UQPAY_SCENES_ID = "scenesid";
  public static final String RESULT_STATE = "state";
  public static final String RESULT_ACCEPT_CODE = "acceptcode";

  public static final String RESULT_QR_CODE_URL = "qrcodeurl";
  public static final String RESULT_QR_CODE_DATA = "qrcode";

  public static final String RESULT_CHANNEL_CODE = "channelcode";
  public static final String RESULT_CHANNEL_MESSAGE = "channelmsg";

  public static final String BANK_CARD_FIRST_NAME = "firstname";
  public static final String BANK_CARD_LAST_NAME = "lastname";
  public static final String BANK_CARD_CARD_NUM = "cardnum";
  public static final String BANK_CARD_CVV = "cvv";
  public static final String BANK_CARD_EXPIRE_MONTH = "expiremonth";
  public static final String BANK_CARD_EXPIRE_YEAR = "expireyear";
  public static final String BANK_CARD_ADDRESS_COUNTRY = "addresscountry";
  public static final String BANK_CARD_ADDRESS_STATE = "addressstate";
  public static final String BANK_CARD_ADDRESS_CITY = "addresscity";
  public static final String BANK_CARD_ADDRESS = "address";
  public static final String BANK_CARD_PHONE = "phone";
  public static final String BANK_CARD_EMAIL = "email";
  public static final String BANK_CARD_ZIP = "zip";
  public static final String BANK_CARD_TYPE = "cardtype";

  public static final String MERCHANT_HOST_ENROLL_CODE_UQPAY_ID = "codeuqpayid";
  public static final String MERCHANT_HOST_VERIFY_CODE = "verifycode";
  public static final String SERVER_HOST_CARD_TOKEN = "token";

  public static final String PAYGATE_API_PAY = "/pay";
  public static final String PAYGATE_API_REFUND = "/refund";
  public static final String PAYGATE_API_CANCEL = "/cancel";
  public static final String PAYGATE_API_QUERY = "/query";
  public static final String PAYGATE_API_PRE_AUTH = "/preauth";
  public static final String PAYGATE_API_ENROLL = "/enroll";
  public static final String PAYGATE_API_VERIFY = "/verify";
}
