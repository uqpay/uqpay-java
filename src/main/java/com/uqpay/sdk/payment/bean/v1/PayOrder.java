package com.uqpay.sdk.payment.bean.v1;

import com.uqpay.sdk.hostui.bean.HostUICard;
import com.uqpay.sdk.utils.Constants;
import javax.validation.constraints.NotEmpty;

/**
 * Order Data
 * all this info will be return to your system when the pay request is done
 */
public class PayOrder extends OrderDTO {
  private static final long serialVersionUID = -2986724242928000769L;

  /**
   * is required
   */
  @ParamLink(Constants.ORDER_TRANS_NAME)
  @NotEmpty
  private String transName; // product info

  @ParamLink(Constants.AUTH_MERCHANT_ID)
  private int merchantId; // only valued if your an Agent, set this value of your sub merchant id

  @ParamExtendLink
  private BankCardExtendDTO bankCard; // required when credit card payment

  @ParamExtendLink
  private ServerHostDTO serverHost; // required when server host payment

  @ParamExtendLink
  private MerchantHostDTO merchantHost; // required when merchant host payment

  @ParamExtendLink
  private ThreeDFinishDTO threeDFinish; // required when finish the 3D payment

  @ParamExtendLink
  private HostUICard hostPayDTO; // required when ui host payment

  public int getMerchantId() {
    return merchantId;
  }

  public void setMerchantId(int merchantId) {
    this.merchantId = merchantId;
  }

  public String getTransName() {
    return transName;
  }

  public void setTransName(String transName) {
    this.transName = transName;
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

  public ThreeDFinishDTO getThreeDFinish() {
    return threeDFinish;
  }

  public void setThreeDFinish(ThreeDFinishDTO threeDFinish) {
    this.threeDFinish = threeDFinish;
  }

  public HostUICard getHostPayDTO() {
    return hostPayDTO;
  }

  public void setHostPayDTO(HostUICard hostPayDTO) {
    this.hostPayDTO = hostPayDTO;
  }
}
