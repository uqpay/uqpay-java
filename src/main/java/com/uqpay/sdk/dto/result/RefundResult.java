package com.uqpay.sdk.dto.result;

import com.uqpay.sdk.utils.Constants;
import com.uqpay.sdk.utils.UqpayTradeType;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

public class RefundResult implements Serializable{
  private static final long serialVersionUID = -6320236770349138351L;
  private String sign;		    //签名（参见3.签名与传参）
  private int merchantId;
  private UqpayTradeType tradeType;		//交易类型，详见附录1
  private String code;		    //结果码
  private String message;		//结果描述
  private Date date;		    //请求的时间，使用Unix时间戳

  private String orderId;		//商户订单ID，在商户端唯一
  private long uqOrderId;	    //UQPAY端的订单ID
  private double amount;		//订单金额
  private String state;			//订单状态
  private String extendInfo;	//商户订单传入的Json编码的扩展信息

  public RefundResult() {}

  public RefundResult(Map<String, Object> mapResult) {
    this.code = (String) mapResult.get(Constants.RESULT_CODE);
    this.sign = (String) mapResult.get(Constants.AUTH_SIGN);
    this.merchantId = Integer.valueOf(mapResult.get(Constants.AUTH_MERCHANT_ID).toString());
    this.tradeType = UqpayTradeType.valueOf((String) mapResult.get(Constants.PAY_OPTIONS_TRADE_TYPE));
    this.message = (String) mapResult.get(Constants.RESULT_MESSAGE);
    this.date = new Date(Long.valueOf(mapResult.get(Constants.ORDER_DATE).toString()));
    this.orderId = (String) mapResult.get(Constants.ORDER_ID);
    this.uqOrderId = Long.valueOf(mapResult.get(Constants.RESULT_UQPAY_ORDER_ID).toString());
    this.amount = Double.valueOf(mapResult.get(Constants.ORDER_AMOUNT).toString());
    this.state = (String) mapResult.get(Constants.RESULT_STATE);
    this.extendInfo = (String) mapResult.get(Constants.ORDER_EXTEND_INFO);
  }

  public String getSign() {
    return sign;
  }

  public void setSign(String sign) {
    this.sign = sign;
  }

  public int getMerchantId() {
    return merchantId;
  }

  public void setMerchantId(int merchantId) {
    this.merchantId = merchantId;
  }

  public UqpayTradeType getTradeType() {
    return tradeType;
  }

  public void setTradeType(UqpayTradeType tradeType) {
    this.tradeType = tradeType;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public String getOrderId() {
    return orderId;
  }

  public void setOrderId(String orderId) {
    this.orderId = orderId;
  }

  public long getUqOrderId() {
    return uqOrderId;
  }

  public void setUqOrderId(long uqOrderId) {
    this.uqOrderId = uqOrderId;
  }

  public double getAmount() {
    return amount;
  }

  public void setAmount(double amount) {
    this.amount = amount;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public String getExtendInfo() {
    return extendInfo;
  }

  public void setExtendInfo(String extendInfo) {
    this.extendInfo = extendInfo;
  }
}
