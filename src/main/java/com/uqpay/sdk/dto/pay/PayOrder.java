package com.uqpay.sdk.dto.pay;

import com.uqpay.sdk.dto.ParamLink;
import com.uqpay.sdk.dto.common.OrderDTO;
import com.uqpay.sdk.utils.Constants;
import javax.validation.constraints.NotEmpty;

/**
 * Order Data
 * all this info will be return to your system when the pay request is done
 */
public class PayOrder extends OrderDTO {
  private static final long serialVersionUID = -2670602967249251032L;

  /**
   * is required
   */
  @ParamLink(Constants.ORDER_TRANS_NAME)
  @NotEmpty
  private String transName; // product info

  public String getTransName() {
    return transName;
  }

  public void setTransName(String transName) {
    this.transName = transName;
  }

}
