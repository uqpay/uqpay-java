package com.uqpay.sdk.payment;

import com.uqpay.sdk.UQPay;
import com.uqpay.sdk.bean.ApiResponse;
import com.uqpay.sdk.exception.UqpayPayFailException;
import com.uqpay.sdk.payment.bean.result.DigiccyResult;
import com.uqpay.sdk.payment.bean.tx.*;
import com.uqpay.sdk.payment.bean.v1.*;
import com.uqpay.sdk.payment.bean.result.OnlineResult;
import com.uqpay.sdk.utils.enums.*;
import com.uqpay.sdk.exception.UqpayRSAException;
import com.uqpay.sdk.exception.UqpayResultVerifyException;
import com.uqpay.sdk.utils.*;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.HibernateValidator;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import java.io.IOException;
import java.util.*;

public class Payment {

  private UQPay uqPay;
  private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

  private <T> void validateRequestParams(T object, String msg) {
    if (object == null) throw new ConstraintViolationException(msg, null);
    Set<ConstraintViolation<T>> violations = validator.validate(object);
    if (violations.size() > 0) {
      throw new ConstraintViolationException(msg, violations);
    }
  }

  private void validatePayData(PaygateParams... params) {
    Arrays.asList(params).forEach(paygateParams -> validateRequestParams(paygateParams, "pay data invalid for uqpay payment"));
  }

  public Payment(UQPay uqPay) {
    this.uqPay = uqPay;
  }

  private String getUrl(String path) {
    return uqPay.getPayUrl(path);
  }

  /****
    Pay
  ****/

  public ApiResponse<TransResult> onlineQR(PayOrder order) throws IOException, UqpayRSAException, UqpayResultVerifyException, UqpayPayFailException {
    order.setTradeType(UqpayTransType.pay);
    validatePayData(order);
    if (order.getScanType() == null) throw new NullPointerException("uqpay qr code payment need Scan Type");
    if (order.getScanType().equals(UqpayScanType.Merchant) && order.getIdentity() == null)
      throw new NullPointerException("uqpay qr code payment need the identity data when scan type is merchant");
    Map<String, String> paramsMap = PayUtil.params2Map(order);
    return uqPay.request(paramsMap, getUrl(Constants.PAYGATE_API_PAY), TransResult.class);
  }

  public ApiResponse<TransResult> offlineQR(PayOrder order) throws IOException, UqpayRSAException, UqpayResultVerifyException, UqpayPayFailException {
    order.setTradeType(UqpayTransType.pay);
    validatePayData(order);
    if (order.getIdentity() == null && order.getScanType().equals(UqpayScanType.Merchant))
      throw new NullPointerException("uqpay offline qr code payment need the identity data");
    if (order.getMerchantCity() == null) {
      throw new NullPointerException("uqpay offline qr code payment need the merchant city data");
    }
    if (order.getTerminalID() == null) {
      throw new NullPointerException("uqpay offline qr code payment need the terminal id data");
    }
    Map<String, String> paramsMap = PayUtil.params2Map(order);
    return uqPay.request(paramsMap, getUrl(Constants.PAYGATE_API_PAY), TransResult.class);
  }

  public ApiResponse<RedirectPostData> online_v1(PayOrder order) throws UqpayRSAException {
    if (order.getReturnUrl() == null || order.getReturnUrl().equals("")){
      throw new NullPointerException("uqpay online payment need sync notice url");
    }
    if (order.getCardNum() != null && order.getCardNum().length() > 0) {
      // use UQPAY public RSA Key to sign the card num.
      order.setCardNum(uqPay.getSecureConfig().encrypt(order.getCardNum()));
    }
    Map<String, String> paramsMap = PayUtil.params2Map(order);
    uqPay.wrapParams(paramsMap);
    RedirectPostData redirectPost = new RedirectPostData();
    redirectPost.setApiURL(getUrl(Constants.PAYGATE_API_PAY));
    redirectPost.setPostData(paramsMap);
    ApiResponse<RedirectPostData> result = new ApiResponse<>();
    result.setCode(10002);
    result.setSuccess(true);
    result.setData(redirectPost);
    return result;
  }

  public ApiResponse<OnlineResult> online(OnlineTX tx) throws UqpayRSAException, IOException, UqpayResultVerifyException, UqpayPayFailException {
    tx.setTransType(UqpayTransType.pay);
    if (tx.getCardNum() != null && tx.getCardNum().length() > 0) {
      // use UQPAY public RSA Key to sign the card num.
      tx.setCardNum(uqPay.getSecureConfig().encrypt(tx.getCardNum()));
    }
    return uqPay.request(tx, getUrl(Constants.PAYGATE_API_PAY_V2), OnlineResult.class);
  }

  /**
   * use for WeChat web base in-app payment ( Mini Program Pay and Official Account Payment )
   */
  public ApiResponse<OnlineResult> weChatOnline(WeChatOnlineTX tx) throws UqpayRSAException, IOException, UqpayResultVerifyException, UqpayPayFailException {
    tx.setTransType(UqpayTransType.pay);
    if (tx.getChannelInfo() == null) {
      tx.setChannelInfo(new HashMap<>());
    }
    tx.getChannelInfo().put(Constants.WECHAT_CHANNEL_INFO_SUB_APPID, tx.getAppId());
    tx.getChannelInfo().put(Constants.WECHAT_CHANNEL_INFO_SUB_OPENID, tx.getOpenId());
    return uqPay.request(tx, getUrl(Constants.PAYGATE_API_PAY_V2), OnlineResult.class);
  }

  /**
   * use for Real Name payment
   */
  public ApiResponse<OnlineResult> realNameOnline(RealNameOnlineTX tx) throws UqpayRSAException, IOException, UqpayResultVerifyException, UqpayPayFailException {
    tx.setTransType(UqpayTransType.pay);
    if (tx.getExtendInfo() == null) {
      tx.setExtendInfo(new HashMap<>());
    }
    tx.getExtendInfo().put(Constants.ORDER_EXTEND_INFO_REAL_NAME_FIRST_NAME, tx.getFirstName());
    tx.getExtendInfo().put(Constants.ORDER_EXTEND_INFO_REAL_NAME_LAST_NAME, tx.getLastName());
    tx.getExtendInfo().put(Constants.ORDER_EXTEND_INFO_REAL_NAME_CUSTOMER_ID, tx.getCustomerId());
    return uqPay.request(tx, getUrl(Constants.PAYGATE_API_PAY_V2), OnlineResult.class);
  }

  public ApiResponse<DigiccyResult> digiccy(QRCodeTX tx) throws UqpayRSAException, UqpayResultVerifyException, UqpayPayFailException, IOException {
    tx.setTransType(UqpayTransType.pay);
    if (tx.getScanType() == null) throw new NullPointerException("UQPAY: need Scan Type");
    if (tx.getScanType().equals(UqpayScanType.Merchant) && tx.getIdentity() == null)
      throw new NullPointerException("UQPAY: need the identity data when scan type is merchant");
    return uqPay.request(tx, getUrl(Constants.PAYGATE_API_DIGICCY), DigiccyResult.class);
  }

  public ApiResponse<TransResult> bankCard(PayOrder order) throws IOException, UqpayRSAException, UqpayResultVerifyException, UqpayPayFailException {
    order.setTradeType(UqpayTransType.pay);
    switch (order.getMethodId()) {
      case PayMethod.AMEX:
      case PayMethod.JCB:
      case PayMethod.Master:
      case PayMethod.VISA:
        validatePayData(order.getBankCard());
        break;
      default:
        validatePayData(BankCardDTO.valueOf(order.getBankCard()));
    }
    Map<String, String> paramsMap = PayUtil.params2Map(order, order.getBankCard());
    return uqPay.request(paramsMap, getUrl(Constants.PAYGATE_API_PAY), TransResult.class);
  }

  public ApiResponse<ThreeDResult> threeD(PayOrder order) throws IOException, UqpayRSAException, UqpayResultVerifyException, UqpayPayFailException {
    order.setTradeType(UqpayTransType.pay);
    if (order.getThreeDFinish() == null) {
      validatePayData(order.getBankCard());
    }
    if (order.getPaResCbUrl() == null || order.getPaResCbUrl().equals(""))
      throw new NullPointerException("uqpay 3D Credit Card Payment need url to handle the PaResponse");
    Map<String, String> paramsMap = PayUtil.params2Map(order, order.getBankCard(), order.getThreeDFinish());
    ApiResponse<ThreeDResult> response = uqPay.request(paramsMap, getUrl(Constants.PAYGATE_API_PAY), ThreeDResult.class);
    ThreeDResult threeDResult = response.getData();
    if (threeDResult != null && threeDResult.getState().equals(OrderStateEnum.Paying.name())) {
      if (StringUtils.isNotBlank(threeDResult.getPaRequest()) && StringUtils.isNotBlank(threeDResult.getAscUrl())) {
        Map<String, String> postData = new HashMap<>();
        postData.put("PaReq", threeDResult.getPaRequest());
        postData.put("TermUrl", order.getPaResCbUrl());
        RedirectPostData redirectPostData = new RedirectPostData();
        redirectPostData.setPostData(postData);
        redirectPostData.setApiURL(threeDResult.getAscUrl());
        threeDResult.setRedirectPostData(redirectPostData);
      }
    }
    return response;
  }

  public ApiResponse<TransResult> hostByMerchant(PayOrder order) throws UqpayRSAException, UqpayResultVerifyException, IOException, UqpayPayFailException {
    order.setTradeType(UqpayTransType.pay);
    validatePayData(order.getMerchantHost());
    Map<String, String> paramsMap = PayUtil.params2Map(order, order.getMerchantHost());
    return uqPay.request(paramsMap, getUrl(Constants.PAYGATE_API_PAY), TransResult.class);
  }

  public ApiResponse<TransResult> hostByServer(PayOrder order) throws UqpayRSAException, UqpayResultVerifyException, IOException, UqpayPayFailException {
    order.setTradeType(UqpayTransType.pay);
    validatePayData(order.getServerHost());
    Map<String, String> paramsMap = PayUtil.params2Map(order, order.getServerHost());
    return uqPay.request(paramsMap, getUrl(Constants.PAYGATE_API_PAY), TransResult.class);
  }

  public ApiResponse<TransResult> inAPP(PayOrder order) throws IOException, UqpayRSAException, UqpayResultVerifyException, UqpayPayFailException {
    order.setTradeType(UqpayTransType.pay);
    if (order.getClient().equals(ClientType.Web))
      throw new NullPointerException("uqpay in-app payment not support pc client");
    Map<String, String> paramsMap = PayUtil.params2Map(order);
    return uqPay.request(paramsMap, getUrl(Constants.PAYGATE_API_PAY), TransResult.class);
  }

  //===========================================
  // Enroll API
  //===========================================

  private ApiResponse<EnrollResult> EnrollCard(EnrollOrder order) throws UqpayRSAException, UqpayResultVerifyException, IOException, UqpayPayFailException {
    Map<String, String> paramsMap = PayUtil.params2Map(order);
    return uqPay.request(paramsMap, getUrl(Constants.PAYGATE_API_ENROLL), EnrollResult.class);
  }

  public ApiResponse<VerifyResult> verify(VerifyOrder order) throws UqpayRSAException, UqpayResultVerifyException, IOException, UqpayPayFailException {
    order.setTradeType(UqpayTransType.verifycode);
    validatePayData(order);
    Map<String, String> paramsMap = PayUtil.params2Map(order);
    return uqPay.request(paramsMap, getUrl(Constants.PAYGATE_API_VERIFY), VerifyResult.class);
  }

  public final ApiResponse<EnrollResult> enroll(EnrollOrder order) throws UqpayRSAException, UqpayResultVerifyException, IOException, UqpayPayFailException {
    order.setTradeType(UqpayTransType.enroll);
    validatePayData(order);
    PayMethodEnum scenes = PayMethodEnum.valueOf(order.getMethodId());
    switch (scenes.getScenes()) {
      case MerchantHost:
        return this.EnrollCard(order);
      case ServerHost:
        return this.EnrollCard(order);
      default:
        return null;
    }
  }

  //===========================================
  // Order Options API
  //===========================================

  public final ApiResponse<RefundResult> refund(OrderRefund refund) throws IOException, UqpayRSAException, UqpayResultVerifyException, UqpayPayFailException {
    validateRequestParams(refund, "refund request data invalid for uqpay order operation");
    Map<String, String> paramsMap = PayUtil.params2Map(refund);
    return uqPay.request(paramsMap, getUrl(Constants.PAYGATE_API_REFUND), RefundResult.class);
  }

  public final ApiResponse<CancelResult> cancel(OrderCancel cancel) throws UqpayRSAException, IOException, UqpayResultVerifyException, UqpayPayFailException {
    validateRequestParams(cancel, "cancel payment request data invalid for uqpay order operation");
    Map<String, String> paramsMap = PayUtil.params2Map(cancel);
    return uqPay.request(paramsMap, getUrl(Constants.PAYGATE_API_CANCEL), CancelResult.class);
  }

  public final ApiResponse<QueryResult> query(OrderQuery query) throws UqpayRSAException, IOException, UqpayResultVerifyException, UqpayPayFailException {
    validateRequestParams(query, "query request data invalid for uqpay order operation");
    Map<String, String> paramsMap = PayUtil.params2Map(query);
    return uqPay.request(paramsMap, getUrl(Constants.PAYGATE_API_QUERY), QueryResult.class);
  }

}
