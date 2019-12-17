package com.uqpay.sdk.payment;

import com.uqpay.sdk.UQPay;
import com.uqpay.sdk.bean.ApiResponse;
import com.uqpay.sdk.config.*;
import com.uqpay.sdk.payment.bean.v1.*;
import com.uqpay.sdk.payment.bean.result.OnlineResult;
import com.uqpay.sdk.payment.bean.tx.OnlineTX;
import com.uqpay.sdk.utils.enums.*;
import com.uqpay.sdk.exception.UqpayRSAException;
import com.uqpay.sdk.exception.UqpayResultVerifyException;
import com.uqpay.sdk.utils.*;
import org.apache.commons.lang3.StringUtils;

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

  public TransResult onlineQR(PayOrder order) throws IOException, UqpayRSAException, UqpayResultVerifyException {
    order.setTradeType(UqpayTransType.pay);
    validatePayData(order);
    if (order.getScanType() == null) throw new NullPointerException("uqpay qr code payment need Scan Type");
    if (order.getScanType().equals(UqpayScanType.Merchant) && order.getIdentity() == null)
      throw new NullPointerException("uqpay qr code payment need the identity data when scan type is merchant");
    Map<String, String> paramsMap = PayUtil.params2Map(order);
    return uqPay.request(paramsMap, getUrl(Constants.PAYGATE_API_PAY), TransResult.class);
  }

  public TransResult offlineQR(PayOrder order) throws IOException, UqpayRSAException, UqpayResultVerifyException {
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

  public ApiResponse<OnlineResult> online(OnlineTX tx) throws UqpayRSAException, IOException, UqpayResultVerifyException {
    tx.setTransType(UqpayTransType.pay);
    ApiResponse<OnlineResult> response = uqPay.request(tx, getUrl(Constants.PAYGATE_API_PAY), OnlineResult.class);
    return response;
  }

  public TransResult bankCard(PayOrder order) throws IOException, UqpayRSAException, UqpayResultVerifyException {
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

  public TransResult threeD(PayOrder order) throws IOException, UqpayRSAException, UqpayResultVerifyException {
    order.setTradeType(UqpayTransType.pay);
    if (order.getThreeDFinish() == null) {
      validatePayData(order.getBankCard());
    }
    if (order.getPaResCbUrl() == null || order.getPaResCbUrl().equals(""))
      throw new NullPointerException("uqpay 3D Credit Card Payment need url to handle the PaResponse");
    Map<String, String> paramsMap = PayUtil.params2Map(order, order.getBankCard(), order.getThreeDFinish());
    ThreeDResult threeDResult = uqPay.request(paramsMap, getUrl(Constants.PAYGATE_API_PAY), ThreeDResult.class);
    if (threeDResult.getState().equals(OrderStateEnum.Paying.name())) {
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
    return threeDResult;
  }

  public TransResult hostByMerchant(PayOrder order) throws UqpayRSAException, UqpayResultVerifyException, IOException {
    order.setTradeType(UqpayTransType.pay);
    validatePayData(order.getMerchantHost());
    Map<String, String> paramsMap = PayUtil.params2Map(order, order.getMerchantHost());
    return uqPay.request(paramsMap, getUrl(Constants.PAYGATE_API_PAY), TransResult.class);
  }

  public TransResult hostByServer(PayOrder order) throws UqpayRSAException, UqpayResultVerifyException, IOException {
    order.setTradeType(UqpayTransType.pay);
    validatePayData(order.getServerHost());
    Map<String, String> paramsMap = PayUtil.params2Map(order, order.getServerHost());
    return uqPay.request(paramsMap, getUrl(Constants.PAYGATE_API_PAY), TransResult.class);
  }

  public TransResult inAPP(PayOrder order) throws IOException, UqpayRSAException, UqpayResultVerifyException {
    order.setTradeType(UqpayTransType.pay);
    if (order.getClient().equals(ClientType.PC_WEB))
      throw new NullPointerException("uqpay in-app payment not support pc client");
    Map<String, String> paramsMap = PayUtil.params2Map(order);
    return uqPay.request(paramsMap, getUrl(Constants.PAYGATE_API_PAY), TransResult.class);
  }

  //===========================================
  // Enroll API
  //===========================================

  private EnrollResult EnrollCard(EnrollOrder order) throws UqpayRSAException, UqpayResultVerifyException, IOException {
    Map<String, String> paramsMap = PayUtil.params2Map(order);
    return uqPay.request(paramsMap, getUrl(Constants.PAYGATE_API_ENROLL), EnrollResult.class);
  }

  public VerifyResult verify(VerifyOrder order) throws UqpayRSAException, UqpayResultVerifyException, IOException {
    order.setTradeType(UqpayTransType.verifycode);
    validatePayData(order);
    Map<String, String> paramsMap = PayUtil.params2Map(order);
    return uqPay.request(paramsMap, getUrl(Constants.PAYGATE_API_VERIFY), VerifyResult.class);
  }

  public final EnrollResult enroll(EnrollOrder order) throws UqpayRSAException, UqpayResultVerifyException, IOException {
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

  public final RefundResult refund(OrderRefund refund) throws IOException, UqpayRSAException, UqpayResultVerifyException {
    validateRequestParams(refund, "refund request data invalid for uqpay order operation");
    Map<String, String> paramsMap = PayUtil.params2Map(refund);
    return uqPay.request(paramsMap, getUrl(Constants.PAYGATE_API_REFUND), RefundResult.class);
  }

  public final CancelResult cancel(OrderCancel cancel) throws UqpayRSAException, IOException, UqpayResultVerifyException {
    validateRequestParams(cancel, "cancel payment request data invalid for uqpay order operation");
    Map<String, String> paramsMap = PayUtil.params2Map(cancel);
    return uqPay.request(paramsMap, getUrl(Constants.PAYGATE_API_CANCEL), CancelResult.class);
  }

  public final QueryResult query(OrderQuery query) throws UqpayRSAException, IOException, UqpayResultVerifyException {
    validateRequestParams(query, "query request data invalid for uqpay order operation");
    Map<String, String> paramsMap = PayUtil.params2Map(query);
    return uqPay.request(paramsMap, getUrl(Constants.PAYGATE_API_QUERY), QueryResult.class);
  }

}
