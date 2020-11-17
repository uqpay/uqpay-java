package com.uqpay.sdk.utils;

public class Constants {
  public static final String AUTH_SIGN = "sign";
  public static final String AUTH_SIGNATURE = "signature";
  public static final String AUTH_SIGN_TYPE = "signtype";
  public static final String AUTH_MERCHANT_ID = "merchantid";
  public static final String AUTH_AGENT_ID = "agentid";

  public static final String PAY_OPTIONS_TRADE_TYPE = "transtype";
  public static final String PAY_OPTIONS_METHOD_ID = "methodid";
  public static final String PAY_OPTIONS_SCAN_TYPE = "scantype";
  public static final String PAY_OPTIONS_ASYNC_NOTICE_URL = "callbackurl";
  public static final String PAY_OPTIONS_SYNC_NOTICE_URL = "returnurl";
  public static final String PAY_OPTIONS_CLIENT_TYPE = "clienttype";
  public static final String PAY_OPTIONS_IDENTITY = "identity";
  public static final String PAY_OPTIONS_MERCHANT_CITY = "merchantcity";
  public static final String PAY_OPTIONS_TERMINAL_ID = "terminalid";
  public static final String PAY_OPTIONS_CLIENT_IP = "clientip";

  public static final String ORDER_ID = "orderid";
  public static final String ORDER_AMOUNT = "amount";
  public static final String ORDER_BILL_AMOUNT = "billamount";
  public static final String ORDER_CURRENCY = "currency";
  public static final String ORDER_TRANS_NAME = "transname";
  public static final String ORDER_DATE = "date";
  public static final String ORDER_FinishTime = "finishTime";
  public static final String ORDER_QUANTITY = "quantity";
  public static final String ORDER_STORE_ID = "storeid";
  public static final String ORDER_SELLER = "seller";
  public static final String ORDER_CHANNEL_INFO = "channelinfo";
  public static final String ORDER_EXTEND_INFO = "extendinfo";
  public static final String ORDER_EXTEND_INFO_ISSUER_LOCALE = "issuerlocale";

  public static final String RESULT_MESSAGE = "message";
  public static final String RESULT_CODE = "code";
  public static final String RESULT_UQPAY_ORDER_ID = "uqorderid";
  public static final String RESULT_UQPAY_RELATED_ID = "relatedid";
  public static final String RESULT_STATE = "state";
  public static final String RESULT_ACCEPT_CODE = "acceptcode";

  public static final String RESULT_QR_CODE_URL = "qrcodeurl";
  public static final String RESULT_QR_CODE_DATA = "qrcode";

  public static final String RESULT_CHANNEL_CODE = "channelcode";
  public static final String RESULT_CHANNEL_MESSAGE = "channelmsg";

  public static final String RESULT_3D_PA_REQUEST = "parequst";
  public static final String RESULT_3D_ASC_URL = "ascurl";

  public static final String BANK_CARD_FIRST_NAME = "firstname";
  public static final String BANK_CARD_LAST_NAME = "lastname";
  public static final String BANK_CARD_CARD_NUM = "cardnum";
  public static final String BANK_CARD_CVV = "cvv";
  public static final String BANK_CARD_EXPIRE_MONTH = "expiremonth";
  public static final String BANK_CARD_EXPIRE_YEAR = "expireyear";
  public static final String BANK_CARD_ADDRESS_COUNTRY = "addresscountry";
  public static final String BANK_CARD_PHONE = "phone";
  public static final String BANK_CARD_EMAIL = "email";
  public static final String BANK_CARD_THREE_D_UQORDERID= "uqorderid";
  public static final String BANK_CARD_THREE_D_PARES= "paresponse";
  public static final String APPLE_PAY_DPAN = "dpan";
  public static final String APPLE_PAY_ICCARDDATA = "iccarddata";
  public static final String APPLE_PAY_CARDHOLDER_NAME= "cardholdername";
  public static final String APPLE_PAY_PIN = "pin";
  public static final String HOSTED_CARDTOKEN = "cardtoken";

  public static final String MERCHANT_HOST_ENROLL_CODE_UQPAY_ID = "codeorderid";
  public static final String MERCHANT_HOST_VERIFY_CODE = "verifycode";
  public static final String SERVER_HOST_CARD_TOKEN = "token";

  public static final String WECHAT_CHANNEL_INFO_SUB_OPENID = "sub_openid";
  public static final String WECHAT_CHANNEL_INFO_SUB_APPID = "sub_appid";

  public static final String PAYGATE_API_PAY = "/pay";
  public static final String PAYGATE_API_PAY_V2 = "/v2/pay";
  public static final String PAYGATE_API_DIGICCY = "/v2/digiccy";
  public static final String PAYGATE_API_HOST_PAY = "/hosted/pay";
  public static final String PAYGATE_API_REFUND = "/refund";
  public static final String PAYGATE_API_CANCEL = "/cancel";
  public static final String PAYGATE_API_QUERY = "/query";
  public static final String PAYGATE_API_PRE_AUTH = "/preauth";
  public static final String PAYGATE_API_ENROLL = "/enroll";
  public static final String PAYGATE_API_VERIFY = "/verify";
  public static final String APPGATE_API_MERCHANT_REGISTER = "/merchant/register";
  public static final String APPGATE_API_MERCHANT_VIEW = "/merchant/view";
  public static final String APPGATE_API_MERCHANT_LIST = "/merchant/list";
  public static final String APPGATE_API_MERCHANT_CHECKING = "/file/checking";
  public static final String APPGATE_API_PRODUCT_CONFIG = "/product/config";
  public static final String APPGATE_API_PRODUCT_LIST = "/product/list";
  public static final String APPGATE_API_EMVCO_CREATE = "/emvco/create";
  public static final String APPGATE_API_EMVCO_QUERY = "/emvco/query";
  public static final String APPGATE_API_EMVCO_PAYLOAD = "/emvco/payload";
  public static final String APPGATE_API_RES_EXCHANGE_RATE = "/res/exchange";
  public static final String APPGATE_API_FINANCE_APPLY_WITHDRAW = "/finance/withdraw";
  public static final String APPGATE_API_FINANCE_BALANCE = "/finance/balance";
  public static final String APPGATE_API_HOST_INIT = "/hosted/init";
}
