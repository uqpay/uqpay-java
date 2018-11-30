package com.uqpay.sdk.dto;

import com.uqpay.sdk.utils.PaymentSupportClient;
import com.uqpay.sdk.utils.UqpayScanType;
import com.uqpay.sdk.utils.UqpayTradeType;
import java.util.Map;

public interface PayOptions {
  public UqpayTradeType getTradeType();

  public void setTradeType(UqpayTradeType tradeType);

  public int getMethodId();

  public void setMethodId(int methodId);

  public String getCallbackUrl();

  public void setCallbackUrl(String callbackUrl);

  public PaymentSupportClient getClient();

  public void setClient(PaymentSupportClient client);

  public String getReturnUrl();

  public void setReturnUrl(String returnUrl);

  public UqpayScanType getScanType();

  public void setScanType(UqpayScanType scanType);

  public String getIdentity();

  public void setIdentity(String identity);

  public Map<String, String> getChannelInfo();

  public void setChannelInfo(Map<String, String> channelInfo);

  public Map<String, String> getExtendInfo();

  public void setExtendInfo(Map<String, String> extendInfo);

  public String getMerchantCity();

  public void setMerchantCity(String merchantCity);

  public String getTerminalID();

  public void setTerminalID(String terminalID);
}
