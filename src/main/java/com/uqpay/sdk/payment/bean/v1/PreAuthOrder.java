package com.uqpay.sdk.payment.bean.v1;

import com.uqpay.sdk.utils.Constants;

public class PreAuthOrder extends OrderDTO {
  private static final long serialVersionUID = -6879616874276192980L;

  @ParamLink(Constants.AUTH_MERCHANT_ID)
  private int merchantId; // only valued if your an Agent, set this value of your sub merchant id

  @ParamLink(Constants.ORDER_TRANS_NAME)
  private String transName; // product info

  @ParamLink(Constants.RESULT_UQPAY_ORDER_ID)
  private long uqOrderId;

  @ParamExtendLink
  private ServerHostDTO serverHost;

  @ParamExtendLink
  private MerchantHostDTO merchantHost;

  @ParamExtendLink
  private BankCardExtendDTO bankCard; // required when credit card payment

  public int getMerchantId() {
    return merchantId;
  }

  public void setMerchantId(int merchantId) {
    this.merchantId = merchantId;
  }

  public BankCardExtendDTO getBankCard() {
    return bankCard;
  }

  public void setBankCard(BankCardExtendDTO bankCard) {
    this.bankCard = bankCard;
  }

  public ServerHostDTO getServerHost() {
    return serverHost;
  }

  public void setServerHost(ServerHostDTO serverHost) {
    this.serverHost = serverHost;
  }

  public MerchantHostDTO getMerchantHost() {
    return merchantHost;
  }

  public void setMerchantHost(MerchantHostDTO merchantHost) {
    this.merchantHost = merchantHost;
  }

  public long getUqOrderId() {
    return uqOrderId;
  }

  public void setUqOrderId(long uqOrderId) {
    this.uqOrderId = uqOrderId;
  }

  public String getTransName() {
    return transName;
  }

  public void setTransName(String transName) {
    this.transName = transName;
  }
}
