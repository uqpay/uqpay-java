package com.uqpay.sdk;

import com.uqpay.sdk.config.*;
import com.uqpay.sdk.dto.PayOptions;
import com.uqpay.sdk.dto.PaygateParams;
import com.uqpay.sdk.dto.common.*;
import com.uqpay.sdk.dto.emvco.EmvcoCreateDTO;
import com.uqpay.sdk.dto.emvco.EmvcoGetPayloadDTO;
import com.uqpay.sdk.dto.enroll.EnrollOrder;
import com.uqpay.sdk.dto.enroll.VerifyOrder;
import com.uqpay.sdk.dto.common.MerchantHostDTO;
import com.uqpay.sdk.dto.exchangeRate.ExchangeRateQueryDTO;
import com.uqpay.sdk.dto.host.HostPayDTO;
import com.uqpay.sdk.dto.host.HostPreInit;
import com.uqpay.sdk.dto.merchant.ConfigPaymentDTO;
import com.uqpay.sdk.dto.merchant.DownloadCheckingFileDTO;
import com.uqpay.sdk.dto.merchant.MerchantRegisterDTO;
import com.uqpay.sdk.dto.merchant.WithdrawDTO;
import com.uqpay.sdk.dto.pay.CashierOrder;
import com.uqpay.sdk.dto.pay.PayOrder;
import com.uqpay.sdk.dto.common.ServerHostDTO;
import com.uqpay.sdk.dto.preAuth.PreAuthOrder;
import com.uqpay.sdk.dto.result.appgate.*;
import com.uqpay.sdk.exception.UqpayPayFailException;
import com.uqpay.sdk.utils.enums.*;
import okhttp3.Request;
import okhttp3.Response;
import com.uqpay.sdk.dto.operation.OrderCancel;
import com.uqpay.sdk.dto.operation.OrderQuery;
import com.uqpay.sdk.dto.operation.OrderRefund;
import com.uqpay.sdk.dto.result.*;
import com.uqpay.sdk.exception.UqpayRSAException;
import com.uqpay.sdk.exception.UqpayResultVerifyException;
import com.uqpay.sdk.utils.*;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class UqpayAPI {

  private PaygateConfig paygateConfig;

  private MerchantConfig merchantConfig;

  private CashierConfig cashierConfig;

  private AppgateConfig appgateConfig;

  private AuthDTO auth;

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

  UqpayAPI(Builder builder) {
    this.paygateConfig = builder.paygateConfig;
    this.merchantConfig = builder.merchantConfig;
    this.appgateConfig = builder.appgateConfig;
    this.cashierConfig = builder.cashierConfig;
    this.auth = new AuthDTO();
    this.auth.setAgentId(merchantConfig.getAgentId());
    this.auth.setMerchantId(merchantConfig.getId());
    if (this.merchantConfig.getConnectTimeout() > 0) {
      PayUtil.httpClient = PayUtil.httpClient.newBuilder().connectTimeout(this.merchantConfig.getConnectTimeout(), TimeUnit.SECONDS).build();
    }
  }

  public PaygateConfig paygateConfig() {
    return this.paygateConfig;
  }
  public MerchantConfig merchantConfig() {
    return this.merchantConfig;
  }
  public AppgateConfig appgateConfig() {
    return this.appgateConfig;
  }
  public CashierConfig cashierConfig() {
    return this.cashierConfig;
  }
  /**
   * use for partner 2 set merchantId, after build UQPAY
   * @param merchantId
   */
  public void updateMerchantId (int merchantId) {
    this.auth.setMerchantId(merchantId);
  }

  public String paygateApiUrl(String url) {
    return paygateConfig.getApiRoot() + url;
  }

  public String appgateApiUrl(String url) {
    return appgateConfig.getApiRoot() + url;
  }

  private void setAuthForJsonParams(BaseJsonRequestDTO jsonParams) {
    jsonParams.setMerchantId(this.auth.getMerchantId());
    jsonParams.setAgentId(this.auth.getAgentId());
  }

  private Map<String, String> directFormPost(Map<String, String> paramsMap, String url) throws IOException, UqpayRSAException, UqpayResultVerifyException, UqpayPayFailException {
    Request request = PayUtil.generateFormRequest(paramsMap, url);
    Response response = PayUtil.doSyncRequest(request);
    Map<String, String> resultMap = Tools.json2map(response.body().string());
    PayUtil.verifyUqpayNotice(resultMap, paygateConfig.getSecure());
    return resultMap;
  }

  private <T> T directFormPost(Map<String, String> paramsMap, String url, Class<T> resultClass) throws IOException, UqpayRSAException, UqpayResultVerifyException, UqpayPayFailException {
    Map<String, String> resultMap = directFormPost(paramsMap, url);
    T result = PayUtil.map2Params(resultClass, resultMap);
    return result;
  }

  private <T> T directJsonPost(Object params, Class<T> resultClass, String url) throws IOException, UqpayRSAException, UqpayResultVerifyException, UqpayPayFailException {
    PayUtil.signParams((BaseJsonRequestDTO) params, appgateConfig.getSecure());
    Request request = PayUtil.generateJsonRequest(params, url);
    Response response = PayUtil.doSyncRequest(request);
    String resBody = response.body().string();
    T result = Tools.json2Obj(resBody, resultClass);
    PayUtil.verifyUqpayNotice(resBody, ((BaseAppgateResult) result).getSignature(), appgateConfig.getSecure());
    return result;
  }

  private byte[] directFileDownload(Object params, String destPath, String url) throws IOException, UqpayRSAException, UqpayPayFailException {
    PayUtil.signParams((BaseJsonRequestDTO) params, appgateConfig.getSecure());
    Request request = PayUtil.generateJsonRequest(params, url);
    byte[] fileContent = PayUtil.doFileDownloadRequest(request, destPath);
    return fileContent;
  }

  /****
    Payment
  ****/

  private final TransResult QRCodePayment(PaygateParams pay, String url) throws IOException, UqpayRSAException, UqpayResultVerifyException, UqpayPayFailException {
    PayOptions payOptions = (PayOptions) pay;
    if (payOptions.getScanType() == null) throw new NullPointerException("uqpay qr code payment need Scan Type");
    if (payOptions.getScanType().equals(UqpayScanType.Merchant) && payOptions.getIdentity() == null)
      throw new NullPointerException("uqpay qr code payment need the identity data when scan type is merchant");
    Map<String, String> paramsMap = PayUtil.generatePayParamsMap(paygateConfig.getSecure(), pay, auth);
    return directFormPost(paramsMap, url, TransResult.class);
  }

  private final TransResult OfflineQRCodePayment(PaygateParams pay, String url) throws IOException, UqpayRSAException, UqpayResultVerifyException, UqpayPayFailException {
    PayOptions payOptions = (PayOptions) pay;
    if (payOptions.getIdentity() == null)
      throw new NullPointerException("uqpay offline qr code payment need the identity data");
    if (payOptions.getMerchantCity() == null) {
      throw new NullPointerException("uqpay offline qr code payment need the merchant city data");
    }
    if (payOptions.getTerminalID() == null) {
      throw new NullPointerException("uqpay offline qr code payment need the terminal id data");
    }
    Map<String, String> paramsMap = PayUtil.generatePayParamsMap(paygateConfig.getSecure(), pay, auth);
    return directFormPost(paramsMap, url, TransResult.class);
  }

  private final TransResult RedirectPayment(PaygateParams pay, String url) throws IOException, UqpayRSAException, UqpayPayFailException {
    PayOptions payOptions = (PayOptions) pay;
    if (payOptions.getReturnUrl() == null || payOptions.getReturnUrl().equals(""))
      throw new NullPointerException("uqpay online payment need sync notice url");
    Map<String, String> paramsMap = PayUtil.generatePayParamsMap(paygateConfig.getSecure(), pay, auth);
    TransResult transResult = new TransResult(paramsMap, url);
    transResult.setState(OrderStateEnum.Ready.name());
    transResult.setMethodId(payOptions.getMethodId());
    transResult.setCode(10002);
    transResult.setMerchantId(auth.getMerchantId());
    transResult.setAgentId(auth.getAgentId());
    transResult.setOrderId(((PayOrder) pay).getOrderId());
    return transResult;
  }

  private final TransResult CreditCardPayment(PaygateParams pay, BankCardExtendDTO bankCard, String url) throws IOException, UqpayRSAException, UqpayResultVerifyException, UqpayPayFailException {
    Map<String, String> paramsMap = PayUtil.generatePayParamsMap(paygateConfig.getSecure(), pay, bankCard, auth);
    return directFormPost(paramsMap, url, TransResult.class);
  }

  private final TransResult MerchantHostPayment(PaygateParams pay, MerchantHostDTO hostDTO, String url) throws UqpayRSAException, UqpayResultVerifyException, UqpayPayFailException, IOException {
    Map<String, String> paramsMap = PayUtil.generatePayParamsMap(paygateConfig.getSecure(), pay, hostDTO, auth);
    return directFormPost(paramsMap, url, TransResult.class);
  }

  private final TransResult ServerHostPayment(PaygateParams pay, ServerHostDTO hostDTO, String url) throws UqpayRSAException, UqpayResultVerifyException, UqpayPayFailException, IOException {
    Map<String, String> paramsMap = PayUtil.generatePayParamsMap(paygateConfig.getSecure(), pay, hostDTO, auth);
    return directFormPost(paramsMap, url, TransResult.class);
  }

  private final TransResult UIHostPayment(PaygateParams pay, HostPayDTO hostDTO, String url) throws UqpayRSAException, UqpayResultVerifyException, UqpayPayFailException, IOException {
    Map<String, String> paramsMap = PayUtil.generatePayParamsMap(paygateConfig.getSecure(), pay, hostDTO, auth);
    return directFormPost(paramsMap, url, TransResult.class);
  }

  private final TransResult ThreeDSecurePayment(PaygateParams pay, BankCardExtendDTO bankCard, ThreeDFinishDTO threeDFinish, String url) throws IOException, UqpayRSAException, UqpayPayFailException, UqpayResultVerifyException {
    PayOptions payOptions = (PayOptions) pay;
    if (payOptions.getPaResCbUrl() == null || payOptions.getPaResCbUrl().equals(""))
      throw new NullPointerException("uqpay 3D Credit Card Payment need url to handle the PaResponse");
    Map<String, String> paramsMap = PayUtil.generatePayParamsMap(paygateConfig.getSecure(), pay, bankCard, threeDFinish, auth);
    Map<String, String> resMap = directFormPost(paramsMap, url);
    TransResult transResult = PayUtil.map2Params(TransResult.class, resMap);
    if (transResult.getState().equals(OrderStateEnum.Paying.name())) {
      ThreeDResult threeDResult = PayUtil.map2Params(ThreeDResult.class, resMap);
      if (StringUtils.isNotBlank(threeDResult.getPaRequest()) && StringUtils.isNotBlank(threeDResult.getAscUrl())) {
        Map<String, String> postData = new HashMap<>();
        postData.put("PaReq", threeDResult.getPaRequest());
        postData.put("TermUrl", payOptions.getPaResCbUrl());
        RedirectPostData redirectPostData = new RedirectPostData();
        redirectPostData.setPostData(postData);
        redirectPostData.setApiURL(threeDResult.getAscUrl());
        transResult.setRedirectPostData(redirectPostData);
      }
    }
    return transResult;
  }

  private final TransResult InAppPayment(PaygateParams pay, String url) throws IOException, UqpayRSAException, UqpayResultVerifyException, UqpayPayFailException {
    if (((PayOptions) pay).getClient().equals(PaymentSupportClient.PC_WEB))
      throw new NullPointerException("uqpay in-app payment not support pc client");
    Map<String, String> paramsMap = PayUtil.generatePayParamsMap(paygateConfig.getSecure(), pay, auth);
    return directFormPost(paramsMap, url, TransResult.class);
  }

  private final TransResult PreAuthFinish(PreAuthOrder order) throws UqpayRSAException, UqpayResultVerifyException, UqpayPayFailException, IOException {
    if (order.getUqOrderId() <= 0) {
      throw new NullPointerException("uqpay order id is required");
    }
    Map<String, String> paramsMap = PayUtil.generatePayParamsMap(paygateConfig.getSecure(), order, auth);
    return directFormPost(paramsMap, paygateApiUrl(Constants.PAYGATE_API_PRE_AUTH), TransResult.class);
  }

  /****
   Enroll
   ****/

  private final EnrollResult EnrollCard(EnrollOrder order) throws UqpayRSAException, UqpayResultVerifyException, UqpayPayFailException, IOException {
    Map<String, String> paramsMap = PayUtil.generatePayParamsMap(paygateConfig.getSecure(), order, auth);
    return directFormPost(paramsMap, paygateApiUrl(Constants.PAYGATE_API_ENROLL), EnrollResult.class);
  }

  private final VerifyResult VerifyPhone(VerifyOrder order) throws UqpayRSAException, UqpayResultVerifyException, UqpayPayFailException, IOException {
    Map<String, String> paramsMap = PayUtil.generatePayParamsMap(paygateConfig.getSecure(), order, auth);
    return directFormPost(paramsMap, paygateApiUrl(Constants.PAYGATE_API_VERIFY), VerifyResult.class);
  }


  //===========================================
  // Pay API
  //===========================================

  /**
   *
   *
   * @param order
   * @return
   * @throws UqpayRSAException
   * @throws UqpayResultVerifyException
   * @throws UqpayPayFailException
   * @throws IOException
   */
  public final TransResult pay(PayOrder order) throws UqpayRSAException, UqpayResultVerifyException, UqpayPayFailException, IOException {
    order.setTradeType(UqpayTransType.pay);
    validatePayData(order);
    PayMethodEnum scenes = PayMethodEnum.valueOf(order.getMethodId());
    switch (scenes.getScenes()) {
      case QRCode:
        return this.QRCodePayment(order, paygateApiUrl(Constants.PAYGATE_API_PAY));
      case OfflineQRCode:
        return this.OfflineQRCodePayment(order, paygateApiUrl(Constants.PAYGATE_API_PAY));
      case OnlinePay:
        return this.RedirectPayment(order, paygateApiUrl(Constants.PAYGATE_API_PAY));
      case InApp:
        return this.InAppPayment(order, paygateApiUrl(Constants.PAYGATE_API_PAY));
      case CreditCard:
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
        return this.CreditCardPayment(order, order.getBankCard(), paygateApiUrl(Constants.PAYGATE_API_PAY));
      case ThreeDCreditCard:
        if (order.getThreeDFinish() == null) {
          validatePayData(order.getBankCard());
        }
        return this.ThreeDSecurePayment(order, order.getBankCard(), order.getThreeDFinish(), paygateApiUrl(Constants.PAYGATE_API_PAY));
      case MerchantHost:
        validatePayData(order.getMerchantHost());
        return this.MerchantHostPayment(order, order.getMerchantHost(), paygateApiUrl(Constants.PAYGATE_API_PAY));
      case ServerHost:
        validatePayData(order.getServerHost());
        return this.ServerHostPayment(order, order.getServerHost(), paygateApiUrl(Constants.PAYGATE_API_PAY));
      default:
        return null;
    }
  }

  public final TransResult hostPay(PayOrder order) throws UqpayRSAException, UqpayResultVerifyException, UqpayPayFailException, IOException {
    order.setTradeType(UqpayTransType.pay);
    validatePayData(order);
    validatePayData(order.getHostPayDTO());
    return this.UIHostPayment(order, order.getHostPayDTO(), paygateApiUrl(Constants.PAYGATE_API_HOST_PAY));
  }

  public final String cashier(CashierOrder order) throws UnsupportedEncodingException, UqpayRSAException {
    validateRequestParams(order, "pay data invalid for uqpay cashier");
    Map<String, String> paramsMap = PayUtil.generatePayParamsMap(cashierConfig.getSecure(), order, auth);
    return cashierConfig.getApiRoot() + "?" + Tools.stringify(paramsMap, true);
  }

  //===========================================
  // PreAuth API
  //===========================================

  /**
   * check the code ,it's easy to understand.
   * be sure set the right trade type
   * @param order required
   * @param bankCard optional
   * @return
   * @throws UqpayRSAException
   * @throws UqpayResultVerifyException
   * @throws UqpayPayFailException
   * @throws IOException
   */
  public final TransResult preAuth(PreAuthOrder order, BankCardExtendDTO bankCard) throws UqpayRSAException, UqpayResultVerifyException, UqpayPayFailException, IOException {
    validatePayData(order);
    switch (order.getTradeType()) {
      case preauth:
        PayMethodEnum scenes = PayMethodEnum.valueOf(order.getMethodId());
        switch (scenes.getScenes()) {
          case InApp:
            return this.InAppPayment(order, paygateApiUrl(Constants.PAYGATE_API_PRE_AUTH));
          case CreditCard:
            validatePayData(bankCard);
            return this.CreditCardPayment(order, bankCard, paygateApiUrl(Constants.PAYGATE_API_PRE_AUTH));
          case OnlinePay:
            validatePayData(bankCard);
            return this.RedirectPayment(order, paygateApiUrl(Constants.PAYGATE_API_PRE_AUTH));
          case MerchantHost:
            validatePayData(order.getMerchantHost());
            return this.MerchantHostPayment(order, order.getMerchantHost(), paygateApiUrl(Constants.PAYGATE_API_PRE_AUTH));
          case ServerHost:
            validatePayData(order.getServerHost());
            return this.ServerHostPayment(order, order.getServerHost(), paygateApiUrl(Constants.PAYGATE_API_PRE_AUTH));
          default:
            return null;
        }
      case preauthcc:
      case preauthcomplete:
      case preauthcancel:
        return this.PreAuthFinish(order);
      default:
        return null;
    }
  }

  //===========================================
  // Enroll API
  //===========================================

  public final EnrollResult enroll(EnrollOrder order) throws UqpayRSAException, UqpayResultVerifyException, UqpayPayFailException, IOException {
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

  public final VerifyResult verify(VerifyOrder order) throws UqpayRSAException, UqpayResultVerifyException, UqpayPayFailException, IOException {
    order.setTradeType(UqpayTransType.verifycode);
    validatePayData(order);
    return this.VerifyPhone(order);
  }

  //===========================================
  // Order Options API
  //===========================================

  public final RefundResult refund(OrderRefund refund) throws IOException, UqpayRSAException, UqpayResultVerifyException, UqpayPayFailException {
    validateRequestParams(refund, "refund request data invalid for uqpay order operation");
    Map<String, String> paramsMap = PayUtil.generatePayParamsMap(paygateConfig.getSecure(), refund, auth);
    return directFormPost(paramsMap, paygateApiUrl(Constants.PAYGATE_API_REFUND), RefundResult.class);
  }

  public final CancelResult cancel(OrderCancel cancel) throws UqpayRSAException, IOException, UqpayResultVerifyException, UqpayPayFailException {
    validateRequestParams(cancel, "cancel payment request data invalid for uqpay order operation");
    Map<String, String> paramsMap = PayUtil.generatePayParamsMap(paygateConfig.getSecure(), cancel, auth);
    return directFormPost(paramsMap, paygateApiUrl(Constants.PAYGATE_API_CANCEL), CancelResult.class);
  }

  public final QueryResult query(OrderQuery query) throws UqpayRSAException, IOException, UqpayResultVerifyException, UqpayPayFailException {
    validateRequestParams(query, "query request data invalid for uqpay order operation");
    Map<String, String> paramsMap = PayUtil.generatePayParamsMap(paygateConfig.getSecure(), query, auth);
    return directFormPost(paramsMap, paygateApiUrl(Constants.PAYGATE_API_QUERY), QueryResult.class);
  }

  //===========================================
  // Merchant Manager API
  //===========================================

  public final BaseAppgateResult register(MerchantRegisterDTO registerDTO) throws UqpayRSAException, UqpayResultVerifyException, UqpayPayFailException, IOException {
    this.setAuthForJsonParams(registerDTO);
    validateRequestParams(registerDTO, "request data invalid for uqpay register merchant");
    return directJsonPost(registerDTO, BaseAppgateResult.class, appgateApiUrl(Constants.APPGATE_API_MERCHANT_REGISTER));
  }

  public final MerchantInfoResult queryMerchantDetail(BaseJsonRequestDTO requestDTO) throws UqpayRSAException, UqpayResultVerifyException, UqpayPayFailException, IOException {
    this.setAuthForJsonParams(requestDTO);
    validateRequestParams(requestDTO, "request data invalid for query merchant detail");
    return directJsonPost(requestDTO, MerchantInfoResult.class, appgateApiUrl(Constants.APPGATE_API_MERCHANT_VIEW));
  }

  public final BaseAppgateResult withdraw(WithdrawDTO withdrawDTO) throws UqpayRSAException, UqpayResultVerifyException, UqpayPayFailException, IOException {
    this.setAuthForJsonParams(withdrawDTO);
    validateRequestParams(withdrawDTO, "request data invalid for withdraw apply");
    return directJsonPost(withdrawDTO, BaseAppgateResult.class, appgateApiUrl(Constants.APPGATE_API_FINANCE_APPLY_WITHDRAW));
  }

  public final BalanceResult queryBalance(BaseJsonRequestDTO requestDTO) throws UqpayRSAException, UqpayResultVerifyException, UqpayPayFailException, IOException {
    this.setAuthForJsonParams(requestDTO);
    validateRequestParams(requestDTO, "request data invalid for query balance");
    return directJsonPost(requestDTO, BalanceResult.class, appgateApiUrl(Constants.APPGATE_API_FINANCE_BALANCE));
  }

  public final MerchantListResult queryMerchantList(PageRequestDTO requestDTO) throws UqpayRSAException, UqpayResultVerifyException, UqpayPayFailException, IOException {
    this.setAuthForJsonParams(requestDTO);
    validateRequestParams(requestDTO, "request data invalid for query merchant list");
    return directJsonPost(requestDTO, MerchantListResult.class, appgateApiUrl(Constants.APPGATE_API_MERCHANT_LIST));
  }

  public final byte[] downloadCheckingFiles(DownloadCheckingFileDTO fileDTO, String destPath) throws UqpayRSAException, IOException, UqpayPayFailException {
    this.setAuthForJsonParams(fileDTO);
    validateRequestParams(fileDTO, "request data invalid for download checking file");
    return directFileDownload(fileDTO, destPath, appgateApiUrl(Constants.APPGATE_API_MERCHANT_CHECKING));
  }

  //===========================================
  // Payment Method Manager API
  //===========================================

  public final ConfigMethodResult configPayMethod(ConfigPaymentDTO paymentDTO) throws UqpayRSAException, UqpayResultVerifyException, UqpayPayFailException, IOException {
    this.setAuthForJsonParams(paymentDTO);
    validateRequestParams(paymentDTO, "request data invalid for config merchant config");
    return directJsonPost(paymentDTO, ConfigMethodResult.class, appgateApiUrl(Constants.APPGATE_API_PRODUCT_CONFIG));
  }

  public final MerchantPayMethodListResult queryConfiguredPayMethod(BaseJsonRequestDTO requestDTO) throws UqpayRSAException, UqpayResultVerifyException, UqpayPayFailException, IOException {
    this.setAuthForJsonParams(requestDTO);
    validateRequestParams(requestDTO, "request data invalid for query configured pay method");
    return directJsonPost(requestDTO, MerchantPayMethodListResult.class, appgateApiUrl(Constants.APPGATE_API_PRODUCT_LIST));
  }

  //===========================================
  // EMVCO QRCode API
  //===========================================

  public final QRCodeResult createQRCode(EmvcoCreateDTO createDTO) throws UqpayRSAException, UqpayResultVerifyException, UqpayPayFailException, IOException {
    this.setAuthForJsonParams(createDTO);
    validateRequestParams(createDTO, "request data invalid for create qr code");
    return directJsonPost(createDTO, QRCodeResult.class, appgateApiUrl(Constants.APPGATE_API_EMVCO_CREATE));
  }

  public final PayloadResult getQRCodePayload(EmvcoGetPayloadDTO payloadDTO) throws UqpayRSAException, UqpayResultVerifyException, UqpayPayFailException, IOException {
    this.setAuthForJsonParams(payloadDTO);
    validateRequestParams(payloadDTO, "request data invalid for get qr code payload");
    return directJsonPost(payloadDTO, PayloadResult.class, appgateApiUrl(Constants.APPGATE_API_EMVCO_PAYLOAD));
  }

  public final MerchantQRCodeListResult queryAllQRCode(PageRequestDTO requestDTO) throws UqpayRSAException, UqpayResultVerifyException, UqpayPayFailException, IOException {
    this.setAuthForJsonParams(requestDTO);
    validateRequestParams(requestDTO, "request data invalid for query all QRCode of merchant");
    return directJsonPost(requestDTO, MerchantQRCodeListResult.class, appgateApiUrl(Constants.APPGATE_API_EMVCO_QUERY));
  }

  //===========================================
  // UQPAY Public Resource API
  //===========================================

  public final ExchangeRateResult queryExchangeRate(ExchangeRateQueryDTO queryDTO) throws UqpayRSAException, UqpayResultVerifyException, UqpayPayFailException, IOException {
    this.setAuthForJsonParams(queryDTO);
    validateRequestParams(queryDTO, "request data invalid for query exchange rate");
    return directJsonPost(queryDTO, ExchangeRateResult.class, appgateApiUrl(Constants.APPGATE_API_RES_EXCHANGE_RATE));
  }


  //===========================================
  // Host pre-init
  //===========================================

  public final HostPreInitResult hostPreInit(HostPreInit preInit) throws UqpayRSAException, UqpayResultVerifyException, UqpayPayFailException, IOException {
    this.setAuthForJsonParams(preInit);
    validateRequestParams(preInit, "request data invalid for host pre-init");
    return directJsonPost(preInit, HostPreInitResult.class, appgateApiUrl(Constants.APPGATE_API_HOST_INIT));
  }

  public static class Builder {
    PaygateConfig paygateConfig;
    MerchantConfig merchantConfig;
    AppgateConfig appgateConfig;
    CashierConfig cashierConfig;

    public Builder() {
    }

    public Builder paygateConfig(PaygateConfig config) {
      if (config == null) throw new NullPointerException("uqpay paygate config == null");
      if (config.getSecure() == null) throw new NullPointerException("uqpay paygate config miss rsa config");
      this.paygateConfig = config;
      return this;
    }

    public Builder merchantConfig(MerchantConfig config) {
      if (config == null) throw new NullPointerException("uqpay merchant config is null");
      if (config.getId() == 0 && config.getAgentId() == 0) throw new NullPointerException("uqpay merchant config miss merchant or agent account id");
      this.merchantConfig = config;
      return this;
    }

    public Builder appgateConfig(AppgateConfig config) {
      if (config == null) throw new NullPointerException("uqpay appgate config == null");
      if (config.getSecure() == null) throw new NullPointerException("uqpay appgate config miss rsa config");
      this.appgateConfig = config;
      return this;
    }

    public Builder cashierConfig(CashierConfig config) {
      if (config == null) throw new NullPointerException("uqpay cashier config == null");
      if (config.getSecure() == null) throw new NullPointerException("uqpay cashier config miss rsa config");
      this.cashierConfig = config;
      return this;
    }

    public UqpayAPI build() {
      if (merchantConfig == null) {
        throw new NullPointerException("uqpay merchant config is null");
      }
      if (appgateConfig == null && cashierConfig == null && paygateConfig == null) {
        throw new NullPointerException("uqpay operation config is missing, must set at least one of the configs");
      }
      return new UqpayAPI(this);
    }
  }
}
