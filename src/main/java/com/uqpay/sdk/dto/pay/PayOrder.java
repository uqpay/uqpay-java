package com.uqpay.sdk.dto.pay;

import com.uqpay.sdk.dto.ParamExtendLink;
import com.uqpay.sdk.dto.ParamLink;
import com.uqpay.sdk.dto.common.BankCardExtendDTO;
import com.uqpay.sdk.dto.common.MerchantHostDTO;
import com.uqpay.sdk.dto.common.OrderDTO;
import com.uqpay.sdk.dto.common.ServerHostDTO;
import com.uqpay.sdk.utils.Constants;
import javax.validation.constraints.NotEmpty;

/**
 * Order Data
 * all this info will be return to your system when the pay request is done
 */
public class PayOrder extends OrderDTO {
  private static final long serialVersionUID = -1128341395642269486L;

  /**
   * is required
   */
  @ParamLink(Constants.ORDER_TRANS_NAME)
  @NotEmpty
  private String transName; // product info

  @ParamExtendLink
  private BankCardExtendDTO bankCard; // required when credit card payment

  @ParamExtendLink
  private ServerHostDTO serverHost; // required when server host payment

  @ParamExtendLink
  private MerchantHostDTO merchantHost; // required when merchant host payment

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
}
