package com.uqpay.sdk.payment;

import com.uqpay.sdk.TestHttpClient;
import com.uqpay.sdk.UQPay;
import com.uqpay.sdk.bean.ApiResponse;
import com.uqpay.sdk.config.EnvEnum;
import com.uqpay.sdk.config.MemberTypeEnum;
import com.uqpay.sdk.exception.UqpayPayFailException;
import com.uqpay.sdk.exception.UqpayRSAException;
import com.uqpay.sdk.exception.UqpayResultVerifyException;
import com.uqpay.sdk.payment.bean.result.OnlineResult;
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

import java.io.IOException;
import java.util.Currency;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;


@DisplayName("Testing UQPAY Java Server Side Library")
public class AgentPaymentTest {

  private UQPay uqPay;
  private PayOrder payOrder;
  private Payment payment;

  @BeforeEach
  public void initUQpay() {
    String partner_prv_key = "-----BEGIN RSA PRIVATE KEY-----\n" +
        "MIIEpAIBAAKCAQEA7nrkEPqeWp/WhLl/sTQNdtQ0kMGzY5SS8wytR00FIDOj/9kv\n" +
        "C2+mCfW/pY5CsIwQKR/bw6Nbe5fCsZbx2j84ioCjizIxX9dCh9ysC8ADcU18gPh7\n" +
        "KNTncfRfte+HR4v1AA6nA2JikGqhw44dvJS92dUh3Eutt2lnk+7fHjnztvJVyC7O\n" +
        "Vo7VQSrb7CjgVfvWwh+XFKu7BDFXXifYj6U+wjoxFZgURXpMry8I9Hr5tVy33zho\n" +
        "BTyT6SjTK9VOaB5NT1hZmBjMJir/Qet9PtS4BqfFf0r6jDnps7Iem1MGCDfUhYC1\n" +
        "GczgXcD71K2zoMqF8Uhwfppj16jlqij2jNWTKwIDAQABAoIBAFc938cSV/HhPVHq\n" +
        "pnsGBtLsyJoYMm8AgE2n2pAV7gUcvycupZYybvR/0W9YPq9lXdgdjoDgduwc1Z2w\n" +
        "EaP8ssuASdP3NbbRAcbABLR7twaxCRYJUMzcLhszAfyFtuCGo8c0lQaY7GPWjn0C\n" +
        "tYAyjc1tuehkSxWo2rp0jWz6WF0ZT1fszULhP3EkT3IjC1DRpZoSf0X6ENvvbMt4\n" +
        "fUEQwUa16uu/M2WTYZOmfdAP5K09ejNAA/w9yEfvEi7gmV90Vbuz902TAQsskU8E\n" +
        "I3Ra14/ilSXWU/5YkShMckuyF1wiDQHRIPvMNflCjSJ20vQp8QjIZO4X5Wpr6JGB\n" +
        "8edrHjkCgYEA+zLGa3+x3aS3EOCsSxCmcdmPjkwbA54L5aqglycRsJKIkj2DOiv4\n" +
        "OSIVpls2tV78dsmGVCPdHWzjODrnqGxfznvRaSNWNBVfnzRcI8JnHX3+y2lhLzhb\n" +
        "NSHowEdhg6rKKw+1CRMLD5LAE/SnjU2XyJ/EpSWLvSTgyn5JwUiWbw0CgYEA8wnh\n" +
        "KRufaAXSyYNfI7xApIpXPEcCEBlx1f+2GhZC3tHrXrILvgT6IsRFRBNezdV42eXC\n" +
        "vkCfCyJMDanx15IJgT/uZXUhbXLaTC+gxUtfB4OmkDi1BAe4e/JUJutZrmBcQXL/\n" +
        "HqR/fNQYbLpdKJa8gJNOiV1XQ++kQA8GlVdpvRcCgYEAjMOeRx0umeq0n2OXiRUS\n" +
        "gJgPBwmE1dkaB6A/D5TYJ99lYrXPtKhxF+sOwMM6fBZ3WUWC3eGfBd8/0QHJUSsx\n" +
        "4O6nocgohVU42Wko/OzyhadWQbyStjhZfAO9fwpBDdyGH+1UYHpoZ1iwBD7EKb3C\n" +
        "ga1uL7FDhkGFKlPslsBLdH0CgYEA6cVw/JeDRw2C6S4iDz9+dkZTDrnGdDHlW1Ax\n" +
        "mvoarDUCzv03ajljWJmtfoObRyW0rvLf1RxXXuBIg0QaSZ5A4j/aUWDPHHXDIFEX\n" +
        "tW6AI7wwNL028H90plQ7OYxboO0zEAlK9/CGaE2iiMLh5K7I9mu6uUo9LC2PscZC\n" +
        "MNf571UCgYBzM1h3iLe6yE/LIMWU5vLSGuWysZqjpdPHVb44v+wo/Jm0MZg8ngDY\n" +
        "1CwUy0mpqheoABqE7QgVdUtDjOuusYcvWQtHImGxFGhiP8cmJ/g6rHokhaRoGtib\n" +
        "878/2K2dliXW9npnMIRaKePoyLz5QXHm/2uoljORJrCFhcv0g/4zfQ==\n" +
        "-----END RSA PRIVATE KEY-----";
    String partner_uq_pub_key = "-----BEGIN PUBLIC KEY-----\n" +
        "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAkAsYPNtb3YPv+sXNBN0s\n" +
        "HX7FMn0/q85hD2+VMPFeeCGW1aLYuvAj8IUT0SjcVMiJl0GkVphJuQMaPzFqK6ns\n" +
        "yy//duxfLCnhM7/I3loKlERGLN+mYhv4TS5KU3yMtyQra21YZ+/GKBqD1xQvvONA\n" +
        "3Y1vxw0TgFiYVgGaGh8ZLKslcoylACCk31IdiAwNW25ha+Dc1jbDcVfQFdHcMGI1\n" +
        "ovseA7Wskrn3DxH15ktWmf3xkZnxMDITeQERcMJ6+nVU4xmq2jscmdruuZNM0Xma\n" +
        "C46cRvBLrMbIlsL1rkQlEuKhWKj+kcJvKWfZpEjeQpblmn6QmF5bHP6Qx3pz2h9/\n" +
        "cQIDAQAB\n" +
        "-----END PUBLIC KEY-----";
    uqPay = UQPay
        .setting(MemberTypeEnum.AGENT, 1005393)
        .env(EnvEnum.LOCAL)
        .decipher(SignTypeEnum.RSA, partner_uq_pub_key, false)
        .encipher(SignTypeEnum.RSA, partner_prv_key, false)
        .httpClient(TestHttpClient.class);
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
  @DisplayName("Testing BankCard")
  void bankCard() {
    payOrder.setMethodId(PayMethod.UnionPayExpressPay);
    payOrder.setCurrency("USD");
    BankCardExtendDTO bankCard = new BankCardExtendDTO();
    bankCard.setCardNum("6250947000000014");
    bankCard.setCvv("123");
    bankCard.setExpireMonth("12");
    bankCard.setExpireYear("33");
    bankCard.setFirstName("test");
    bankCard.setLastName("test");
    bankCard.setAddressCountry("SG");

    payOrder.setBankCard(bankCard);

    payOrder.setMerchantId(1005412); // set sub merchant id
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
    onlineTX.setCurrency("CNY");
    onlineTX.setReturnUrl("https://localhost:8080/sync");

    onlineTX.setSubMerchantID(1005412);
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
  @DisplayName("Testing Query")
  void query() {
    OrderQuery orderQuery = new OrderQuery();
    orderQuery.setDate(new Date());
    orderQuery.setOrderId("1581064320031");

    orderQuery.setMerchantId(1005412); // set sub merchant id
    try {
      ApiResponse<QueryResult> res = payment.query(orderQuery);
      assertNotNull(res.getData());
      assertEquals(0.1, res.getData().getAmount());
    } catch (UqpayRSAException | IOException | UqpayResultVerifyException | UqpayPayFailException e) {
      e.printStackTrace();
    }
  }

}
