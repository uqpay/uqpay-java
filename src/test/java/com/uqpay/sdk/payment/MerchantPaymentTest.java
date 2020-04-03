package com.uqpay.sdk.payment;

import com.uqpay.sdk.TestHttpClient;
import com.uqpay.sdk.UQPay;
import com.uqpay.sdk.bean.ApiResponse;
import com.uqpay.sdk.config.EnvEnum;
import com.uqpay.sdk.config.MemberTypeEnum;
import com.uqpay.sdk.exception.UqpayPayFailException;
import com.uqpay.sdk.exception.UqpayRSAException;
import com.uqpay.sdk.exception.UqpayResultVerifyException;
import com.uqpay.sdk.payment.bean.result.DigiccyResult;
import com.uqpay.sdk.payment.bean.result.OnlineResult;
import com.uqpay.sdk.payment.bean.tx.BasicTX;
import com.uqpay.sdk.payment.bean.tx.OnlineTX;
import com.uqpay.sdk.payment.bean.v1.*;
import com.uqpay.sdk.utils.PayMethod;
import com.uqpay.sdk.utils.enums.ClientType;
import com.uqpay.sdk.utils.enums.OrderStateEnum;
import com.uqpay.sdk.utils.enums.SignTypeEnum;
import com.uqpay.sdk.utils.enums.UqpayScanType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;
import java.util.Currency;
import java.util.Date;


@DisplayName("Testing UQPAY Java Server Side Library")
public class MerchantPaymentTest {

  private UQPay uqPay;
  private PayOrder payOrder;
  private Payment payment;

  @BeforeEach
  public void initUQpay() {
    String mer_uq_pub_key = "-----BEGIN PUBLIC KEY-----\n" +
        "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAhB/ls6tXiaYxejXRttBX\n" +
        "OD0VujO96AdFAAWKf3XvBZpLNuYDFwesRzoYAfwntiZlkbULdczT8jKuGSjL65yV\n" +
        "ZobRtpwhpBdKipTrWKAS98ainOepIbCRtZr/Nm4aF55cChXcHqZCcQU5dSNNg5Jp\n" +
        "qbXwir+CbOsTZreEVLjPeM82ycvHYGoIHKhMR/9HXLxcG76EypliTUTQkE1BoPyV\n" +
        "QOZeDEpmdpXsBoSgH5SL/gIjUGvRV6A3yRU6bRNtIL+XiFF+TIz65sQSyKX9tXUw\n" +
        "FckpgXyAmCaqZ52mfkdVUzvWL96Rxw1H/I39F5seqM6ACBXd33Sp1NZEykdgx4nd\n" +
        "cQIDAQAB\n" +
        "-----END PUBLIC KEY-----";
    String mer_prv_key = "-----BEGIN RSA PRIVATE KEY-----\n" +
        "MIIEowIBAAKCAQEArQyr++lCstZ4I4px5IDbjf9z/WKaOa/UajNgrsXOUpcIsha5\n" +
        "BgImMJARTthFyva2BXclMMtLWoz0jKxbWXu+m73KyiTsEpZbw4evZ1aAs8y1hVjB\n" +
        "RsYw405xHbSfdZfQQBTUlY6Va6oqEhpNmZCnxzwtwmm+v71CF1Z7LnxNYmM6IrQ9\n" +
        "79VuAUyMDRAcJxBL4vjSfsLmB3QPKGeWC2z7j2JNtqhr0lXlGGRgeXj2XXFZGAly\n" +
        "keowUvEL27sWEEMAb/afe0oNdTEdxALtd3IEJYNus9SdzY3QHVj78p/Y4kXBYsX4\n" +
        "3WIVB3akHYXG37oYG92xWEFs3nzaZZI98iG+twIDAQABAoIBAGQNo5KvN4U3Q5cp\n" +
        "ANjhOBBN1r52OD2KUAJnWksyyywtbzWotamnrHT/l0JDAXdsVamrTbF8mUDtpqd/\n" +
        "MAH47igWAB4IYwYMMVpIJT5WYWuTvJAw1O8awEFspTJLsLbI4/tpD9C48+OgK1r0\n" +
        "IlHbtWYYgUya31L1FjVwJyCldgif6okGS0vgRU+/UzgcsaYOlq0a5+HRLjWnNmTv\n" +
        "NhVJiiTd9fUbfGtVa669bsrWyrPDd4luUiUVESQkybAbS2ffpKOypjfrT+I/8Y4E\n" +
        "C9RcVIinf0Tw7DeD7tMzmM6Ppq2Q8dYheuWzk+0C6BPV1EUo9XMQSGwTP8HJW+VS\n" +
        "OyO82yECgYEA4erLLxMWTM2LzFG2u1Xf+GMjcZAS0IaQhC1vwjuKhf0CmvkB2lhh\n" +
        "83muNnc2k70OBKM9TrQSltfwfl2rCJ64Hi20+EZINzRNU//B9woia5xrjQiqg0gs\n" +
        "O1sMlNt2+K4/Pk5R6eKdeGV+zunYw1U1YNmUOsRkDwBugN8NsiKDz7UCgYEAxBex\n" +
        "JJM3fMKLWy2+OHOTT45OyqVkHm3og9hP7s5K8EW0nbjpY4Qn1j5PDngns7Vzw+zF\n" +
        "ryc32DjxKeASn4PKTlwmgggOPgrzYeZEMWsDRgYpNFwQO8/U7nVDYGaieeiTlQ6l\n" +
        "1IE5PLhMRqhoSmfBzMUgVfvo9g+AtGPAE8iJYDsCgYEAipODXrTOkP3kKshU1kSu\n" +
        "xaXKL/a4E8D3FJzqWLI9HkM8PeNQB6b/LmINQsuNZsIovx+Ck6xRWsXKdzjtmLQD\n" +
        "LD/NKh2yXmpupH/Vcrt8sZWZQ0F1lmHHAAGxjf2w1InNsWJJTLX88cUQK8u1ctvp\n" +
        "iibsjb+5wJn7LoGj3Qje4aECgYBE2pzU3uyI3jbYmUNFxy9eq/V2qoRxOt5+DSJk\n" +
        "FAO0QoWdLCSnUOw8CjzwM7idHYW8shLn4bl2Luhfb9KaOEh9I1ZSKkn19xpmsdgY\n" +
        "Eh9gIyGsxPbeSafW4035N5CthcDsgewwpf9XFs+Rr+iO18fxAvbLulyeqerjbHMx\n" +
        "fyTdqQKBgEhzb8UHNBzLFvMetobOi0dD4L9j1/b+fhCciaDbUgzWRRJBjw2a43C0\n" +
        "Kv3P5otNiow7lEl2JA9ITgWee8RvWypTnsw3YZIv/WLXwt/FPVkaLtYEF7sEBx+Q\n" +
        "8bhgiQJjcJ4f0gPPj3DKpF8hcr3cseaB+pfef6CDAianDeLng6qp\n" +
        "-----END RSA PRIVATE KEY-----";
    uqPay = UQPay
        .setting(MemberTypeEnum.MERCHANT, 1005004)
        .env(EnvEnum.LOCAL)
        .decipher(SignTypeEnum.RSA, mer_uq_pub_key, false)
        .encipher(SignTypeEnum.RSA, mer_prv_key, false)
        .httpClient(TestHttpClient.class)
        .setApiUrl("paygate", "http://localhost:8686", "https://paygate.uqpay.net", "https://paygate.uqpay.com");
    payment = new Payment(uqPay);
    payOrder = new PayOrder();
    payOrder.setTransName("product info");
    payOrder.setOrderId(String.valueOf(new Date().getTime()));
    payOrder.setClientIp("127.0.0.1");
    payOrder.setAmount(0.1);
    payOrder.setQuantity(1);
    payOrder.setClient(ClientType.Web);
    payOrder.setCallbackUrl("https://localhost:8080/async");
    payOrder.setDate(new Date());
  }

  @Test
  @DisplayName("Testing Online QR")
  public void onlineQR() {
    payOrder.setMethodId(PayMethod.UnionPayOnlineQR);
    payOrder.setScanType(UqpayScanType.Consumer);
    payOrder.setCurrency(Currency.getInstance("CNY"));
    try {
      ApiResponse<TransResult> res = payment.onlineQR(payOrder);
      TransResult result = res.getData();
      assertNotNull(result.getQrCode());
      assertNotNull(result.getQrCodeUrl());
      assertEquals(OrderStateEnum.Paying.name(), result.getState());
      assertEquals(payOrder.getOrderId(), result.getOrderId());
    } catch (IOException | UqpayRSAException | UqpayResultVerifyException | UqpayPayFailException e) {
      e.printStackTrace();
    }
  }

  @Test
  @DisplayName("Testing Offline QR")
  void offlineQR() {
    payOrder.setMethodId(PayMethod.UnionPayOfflineQR);
    // testing M Scan C
    payOrder.setScanType(UqpayScanType.Merchant);
    payOrder.setIdentity(TestHttpClient.getTestUnionPayConsumerQRCode());
    payOrder.setMerchantCity("HangZhou");
    payOrder.setTerminalID("123456");
    payOrder.setCurrency(Currency.getInstance("SGD"));

    try {
      ApiResponse<TransResult> res = payment.offlineQR(payOrder);
      TransResult result = res.getData();
      assertEquals(OrderStateEnum.Success.name(), result.getState(), "Offline QR Payment should Success");
      assertEquals(payOrder.getOrderId(), result.getOrderId());
    } catch (IOException | UqpayRSAException | UqpayResultVerifyException | UqpayPayFailException e) {
      e.printStackTrace();
    }

    // testing C Scan M
    payOrder.setOrderId(String.valueOf(new Date().getTime()));
    payOrder.setScanType(UqpayScanType.Consumer);
    payOrder.setIdentity(null);
    payOrder.setMerchantCity("HangZhou");
    payOrder.setTerminalID("123456");
    payOrder.setCurrency(Currency.getInstance("SGD"));
    try {
      ApiResponse<TransResult> res = payment.offlineQR(payOrder);
      TransResult result = res.getData();
      assertNotNull(result.getQrCode());
      assertEquals(OrderStateEnum.Ready.name(), result.getState(), "State should be Ready");
      assertEquals(payOrder.getOrderId(), result.getOrderId());
    } catch (IOException | UqpayRSAException | UqpayResultVerifyException | UqpayPayFailException e) {
      e.printStackTrace();
    }
  }

  @Test
  @DisplayName("Testing BankCard")
  void bankCard() {
    payOrder.setMethodId(PayMethod.UnionPayExpressPay);
    payOrder.setCurrency(Currency.getInstance("SGD"));
    BankCardExtendDTO bankCard = new BankCardExtendDTO();
    bankCard.setCardNum("6250947000000014");
    bankCard.setCvv("123");
    bankCard.setExpireMonth("12");
    bankCard.setExpireYear("33");
    bankCard.setFirstName("test");
    bankCard.setLastName("test");
    bankCard.setAddressCountry("SG");

    payOrder.setBankCard(bankCard);
    try {
      ApiResponse<TransResult> res = payment.bankCard(payOrder);
      TransResult result = res.getData();
      assertEquals(OrderStateEnum.SyncSuccess.name(), result.getState(), "State should be SyncSuccess");
      assertEquals(payOrder.getOrderId(), result.getOrderId());
    } catch (IOException | UqpayRSAException | UqpayResultVerifyException | UqpayPayFailException e) {
      e.printStackTrace();
    }
  }

  @Test
  @DisplayName("Testing Online")
  void online() {
    OnlineTX onlineTX = new OnlineTX();
    onlineTX.setMethodId(PayMethod.UnionSecurePay);
    onlineTX.setAmount(0.1);
    onlineTX.setTransName("product info");
    onlineTX.setCallbackUrl("127.0.0.1");
    onlineTX.setQuantity(1);
    onlineTX.setClientType(ClientType.Web);
    onlineTX.setCallbackUrl("https://localhost:8080/async");
    onlineTX.setClientIp("127.0.0.1");
    onlineTX.setOrderId(String.valueOf(new Date().getTime()));
    onlineTX.setCurrency("SGD");
    onlineTX.setReturnUrl("https://localhost:8080/sync");
    try {
      ApiResponse<OnlineResult> res = payment.online(onlineTX);
      if (res.isSuccess()) {
        assertEquals(res.getCode(), 10001);
        assertNotNull(res.getData(), "Should get the payment result");
        assertNotNull(res.getData().getTargetUrl());
        assertTrue(res.getData().getParams().size()>0);
      } else {
        System.out.println(res.getMessage());
      }
    } catch (UqpayRSAException | IOException | UqpayResultVerifyException | UqpayPayFailException e) {
      e.printStackTrace();
    }
  }

  @Test
  @DisplayName("Testing Digiccy")
  void digiccy() {
    BasicTX tx = new BasicTX();
    tx.setMethodId(PayMethod.DIGICCY);
    tx.setAmount(0.001);
    tx.setTransName("product info");
    tx.setCallbackUrl("127.0.0.1");
    tx.setQuantity(1);
    tx.setClientType(ClientType.Web);
    tx.setCallbackUrl("https://localhost:8080/async");
    tx.setClientIp("127.0.0.1");
    tx.setOrderId(String.valueOf(new Date().getTime()));
    tx.setCurrency("USDT");
    try {
      ApiResponse<DigiccyResult> res = payment.digiccy(tx);
      if (res.isSuccess()) {
        assertEquals(res.getCode(), 10001);
        assertNotNull(res.getData(), "Should get the payment result");
        assertNotNull(res.getData().getAddress());
        assertNotNull(res.getData().getQrCode());
        assertNotNull(res.getData().getQrCodeUrl());
        System.out.println(res.getData().getAddress());
        System.out.println(res.getData().getQrCodeUrl());
      }
    } catch (UqpayRSAException | UqpayResultVerifyException | UqpayPayFailException | IOException e) {
      e.printStackTrace();
    }

  }

  @Test
  @DisplayName("Testing Query")
  void query() {
    OrderQuery orderQuery = new OrderQuery();
    orderQuery.setDate(new Date());
    orderQuery.setOrderId("58I47p198B");

    try {
      ApiResponse<QueryResult> res = payment.query(orderQuery);
      QueryResult result = res.getData();
      assertNotNull(res);
      assertEquals(10, result.getAmount());
    } catch (UqpayRSAException | IOException | UqpayResultVerifyException | UqpayPayFailException e) {
      e.printStackTrace();
    }

  }

}
