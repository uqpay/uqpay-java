package com.uqpay.sdk.payment;

import com.uqpay.sdk.TestHttpClient;
import com.uqpay.sdk.UQPay;
import com.uqpay.sdk.bean.ApiResponse;
import com.uqpay.sdk.config.EnvEnum;
import com.uqpay.sdk.config.MemberTypeEnum;
import com.uqpay.sdk.exception.UqpayPayFailException;
import com.uqpay.sdk.exception.UqpayRSAException;
import com.uqpay.sdk.exception.UqpayResultVerifyException;
import com.uqpay.sdk.operation.Emvco;
import com.uqpay.sdk.operation.bean.EmvcoCreateDTO;
import com.uqpay.sdk.operation.bean.result.QRCodeResult;
import com.uqpay.sdk.payment.bean.result.DigiccyResult;
import com.uqpay.sdk.payment.bean.result.OnlineResult;
import com.uqpay.sdk.payment.bean.tx.AuthQuickTX;
import com.uqpay.sdk.payment.bean.tx.OnlineTX;
import com.uqpay.sdk.payment.bean.tx.QRCodeTX;
import com.uqpay.sdk.payment.bean.v1.*;
import com.uqpay.sdk.utils.PayMethod;
import com.uqpay.sdk.utils.enums.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;
import java.util.Date;


@DisplayName("Testing UQPAY Java Server Side Library")
public class MerchantPaymentTest {

  private UQPay uqPay;
  private PayOrder payOrder;
  private Payment payment;
  private Emvco emvco;

  @BeforeEach
  public void initUQpay() {
    String mer_uq_pub_key = "-----BEGIN PUBLIC KEY-----\n" +
        "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAgETPzkWUkaPlzafiKbIp\n" +
        "9S6alU855A2DPN6DCevjOllCZFPQgI2JnmFrLN4vic26Vdd/6pqqZblrNcwX6lEO\n" +
        "WXS9fzGFQpYT6a6o9Ix1bS3XXp0HHSC3w99Lto0rsHyInZysOGlwIQji4LWMo0hj\n" +
        "tSAB2LfiOTric5twieR7zx0wL4/rmPVQAo8b5OzrnF9VyIrDRhIWXUh6ATa+tjhL\n" +
        "ZoNtnOMWU42LfILhi0nGPQ6jgOZNxRy00revo+Mg9RWMJhHKMzIV0PIOdPVBaGSD\n" +
        "dAp3ItCyi3D9AEOYwEUNzbHBO35eH0DhyGurwMvWFyVt8umyWnm52s6FuXz0aHzv\n" +
        "GwIDAQAB\n" +
        "-----END PUBLIC KEY-----";
    String mer_prv_key = "-----BEGIN RSA PRIVATE KEY-----\n" +
        "MIIEogIBAAKCAQEAhNaXSL6FBeCc+BrXSw0w6+t+zN7/eec2FyrnWL3zT6G76/hP\n" +
        "LgwUapeFon74ju5wi2K2xvDZdAR0ks+iDR1fG7U77w0EDeedTaxbOIjF5CkS0H03\n" +
        "vhlyJ2MKHd7aoMG643B3EbywD5a3d3mE9y2vnc9WMN7c/GppVoyagLmlybbeOOAC\n" +
        "18D7+Zncs1uNq6a+gTNQ24oArk/JmbEZFKVgwGMxDoMTdkxDwE3p/L9iqieBKKUR\n" +
        "y/Ru2xwXYAZ8MFbdZUV7XX4pUn74XEbOW1BhLyLPqUp1V1+PDpjc+SEyk++E4Qcn\n" +
        "7N/+5guaYeVwWTv99aRVgkIwvDbYCgfnm/XMdQIDAQABAoIBACiwOkKT6Nb2B96P\n" +
        "Cib48WUyCvLh00sZoR3TjG1IMYDQv2j18/rcxFwp8UrmrWfKbTqkj+u71j+NJc/a\n" +
        "PX7a4kjwaF0+lWFU8n/aLU7Rxhuu4Q5vbGWUEb0yvuSLYFghPwdvaWlLbHo55hR4\n" +
        "alvz6HjkCziI6xN93KogAysusjNokvDH0XkpmT3f834CFoLvaw390appmqJuc12i\n" +
        "+LinR56J7gYUEdadnF4RsuZpw6NEG3CUOJQVoZVUCMe1CyxdcQtVFOXLNQ5tGNM1\n" +
        "kAPdIXtPVaTPUneFiaDEcOIdEdTRkIz7f/e5mbugLFh0uGmQp/8AbzPC9DDXDogk\n" +
        "631y9XkCgYEA/9SNUgLhfRoO8YQib5Esc5rVnQZnYT8p1VVLpLIQpG9OlGQ6QFn6\n" +
        "o+vI5+n6DmLwOv3u03zE02hoY9sEy/zbhyJbYG3ghC8yTgng0U44jwTccXwkrGZm\n" +
        "oPg2lBGuvkotpHEQFAq3cjLUWrm4zF8xulWoI1W+H7fdPq1jSe4fMEMCgYEAhO0m\n" +
        "qjAEN/Li3sOEqJyIJGtd4qhu5ZL+zjy2/db2kly+CefalyNdjGYhXumSHP6Nuqr1\n" +
        "YW06mri+y3j+5Z4P3SASekpWhkffYhyNEq/uj6qVUjZeSgO4DfcwjaJ6/9SxttSp\n" +
        "JVbTn25aEHAf+ooH/+/3HiZD2ApAW0QQBll1wOcCgYBzuuSfN/xninU+HmcxjzvL\n" +
        "pDyEB1SW8mrrPeW1QHQ03sFucZTEba/rnYtKFldvUKSaGyuB8oxbkny+x2J5IQ7y\n" +
        "J8GscqhBQ9R+5wsTxE3jrPBISj+Q2dYNdZvDCejB3m6dWCRM3Lg16faUgDWwBlRJ\n" +
        "ldw09+HFGXj1lJw44oarXwKBgC03UmCv5q91cpDeJ0EHxhPFZfFU5Cw5nW1gH8sn\n" +
        "FUbiP061TJj+0bKRhyZ3A1nTiTiHMOMMOdQppdUm+mX3J2RLpZ1trhMNXcJM/fvu\n" +
        "VpMOLq8BiX9Z1oEBpcV4EKj3m+AaZNMrvt7Ltd1Dls0tqNz5rrDVyVwy2INzGpRe\n" +
        "V/zfAoGAdqjzk62Z30fJL1MqdaM81vtew0wExEM6q/3vbCKLg9lZO9aKix4ZKnnE\n" +
        "tdfVOqNUqksTqVlucggY05P8OKQe5DoejFInCIslfEdy0S66roFw0f+jGqIWanQ3\n" +
        "pZZ/TDSg9PdHHpLTLZg8MwMgejRCwAEHkQ01t/hi4LKmgmDpTOs=\n" +
        "-----END RSA PRIVATE KEY-----";
    uqPay = UQPay
        .setting(MemberTypeEnum.MERCHANT, 10001)
        .env(EnvEnum.PROD)
        .decipher(SignTypeEnum.RSA, mer_uq_pub_key, false)
        .encipher(SignTypeEnum.RSA, mer_prv_key, false)
        .httpClient(TestHttpClient.class)
        .setApiUrl("paygate", "http://localhost:8686", "https://paygate.uqpay.net", "https://paygate.uqpay.net");
    payment = new Payment(uqPay);
    emvco = new Emvco(uqPay);

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
    payOrder.setCurrency("CNY");
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
    payOrder.setCurrency("SGD");

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
    payOrder.setCurrency("SGD");
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
    payOrder.setCurrency("SGD");
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
  @DisplayName("Testing BankCard")
  void BankCard3D() {
    payOrder.setMethodId(PayMethod.VISA3D);
    payOrder.setCurrency("SGD");
    BankCardExtendDTO bankCard = new BankCardExtendDTO();
    bankCard.setCardNum("4000000000001091");
    bankCard.setCvv("555");
    bankCard.setExpireMonth("12");
    bankCard.setExpireYear("33");
    bankCard.setFirstName("test");
    bankCard.setLastName("test");
    bankCard.setAddressCountry("SG");

    payOrder.setBankCard(bankCard);
    try {
      ApiResponse<RedirectPostData> res = payment.credit3D(payOrder);
      if (res.isSuccess()) {
        assertEquals(res.getCode(), 10001);
        assertNotNull(res.getData(), "Should get the payment result");
        assertNotNull(res.getData().getApiURL());
        assertTrue(res.getData().getPostData().size()>0);
      } else {
        System.out.println(res.getMessage());
      }
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
    QRCodeTX tx = new QRCodeTX();
    tx.setMethodId(PayMethod.DIGICCY);
    tx.setAmount(0.001);
    tx.setTransName("product info");
    tx.setQuantity(1);
    tx.setClientType(ClientType.Web);
    tx.setCallbackUrl("https://localhost:8080/async");
    tx.setClientIp("127.0.0.1");
    tx.setOrderId(String.valueOf(new Date().getTime()));
    tx.setCurrency("USDT");
    tx.setScanType(UqpayScanType.Consumer);
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
  @DisplayName("Testing AuthQuick")
  void authQuick(){
    AuthQuickTX tx = new AuthQuickTX();
    tx.setMethodId(PayMethod.AuthQuickPay);
    tx.setAmount(10);
    tx.setCurrency("CNY");
    tx.setTransName("product info");
    tx.setCallbackUrl("https://localhost:8080/async");
    tx.setClientIp("127.0.0.1");
    tx.setClientType(ClientType.Web);
    tx.setIdNo("11111111111111111111111111111111");
    tx.setCardNum("6288888888888888");
    tx.setPhone("18918981898");
    tx.setRealName("RealName");
    try {
      ApiResponse<TransResult> res = payment.authQuick(tx);
      if (res.isSuccess()) {
        assertEquals(res.getCode(), 10001);
        assertNotNull(res.getData(), "Should get the payment result");
        assertNotNull(res.getData().getQrCode());
        assertNotNull(res.getData().getQrCodeUrl());
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

  @Test
  @DisplayName("Testing Emvco Cteate")
  void emvco() {
    EmvcoCreateDTO emvcoCreateDTO = new EmvcoCreateDTO();
    emvcoCreateDTO.setAmount(1.00);
    emvcoCreateDTO.setCity("SG");
    emvcoCreateDTO.setCodeType(QRCodeTypeEnum.Static);
    emvcoCreateDTO.setType(QRCodeChannelTypeEnum.UnionPay);
    emvcoCreateDTO.setName("test");
    emvcoCreateDTO.setShopName("test");
    emvcoCreateDTO.setTerminalId("10001000");
    try {
      QRCodeResult result = emvco.createQRCode(emvcoCreateDTO);
      assertNotNull(result);
      assertNotNull(result.getPayload());
    } catch (UqpayRSAException | UqpayResultVerifyException | IOException | UqpayPayFailException e) {
      e.printStackTrace();
    }
  }

}
