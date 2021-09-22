package com.uqpay.sdk.operation;

import com.uqpay.sdk.TestHttpClient;
import com.uqpay.sdk.UQPay;
import com.uqpay.sdk.config.EnvEnum;
import com.uqpay.sdk.config.MemberTypeEnum;
import com.uqpay.sdk.exception.UqpayPayFailException;
import com.uqpay.sdk.exception.UqpayRSAException;
import com.uqpay.sdk.exception.UqpayResultVerifyException;
import com.uqpay.sdk.operation.bean.ExternalExchangeDTO;
import com.uqpay.sdk.operation.bean.MerchantRegisterDTO;
import com.uqpay.sdk.operation.bean.result.BaseAppgateResult;
import com.uqpay.sdk.utils.enums.SignTypeEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class MerchantTest {
  private UQPay uqPay;
  private Merchant merchant;
  private InterPub interPub;
  @BeforeEach
  void setUp() {
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
    merchant = new Merchant(uqPay);
    interPub = new InterPub(uqPay);
  }

  @Test
  @DisplayName("Testing Internal Public API")
  void extExchange(){
    ExternalExchangeDTO dto = new ExternalExchangeDTO();
    dto.setExchangeRate(6.5);
    dto.setAmount(65.0);
    dto.setCostAmount(10.0);
    dto.setOriginalCurrency("USD");
    dto.setTargetCurrency("CNH");
    try {
      BaseAppgateResult result = interPub.queryExchangeRate(dto);
      assertNotNull(result);
      assertTrue(result.getMerchantId() > 0);
      assertEquals(result.getAgentId(), 1005393);
    } catch (UqpayRSAException | UqpayResultVerifyException | IOException | UqpayPayFailException e) {
      e.printStackTrace();
    }
  }

  @Test
  @DisplayName("Testing Register Merchant")
  void register() {
    MerchantRegisterDTO dto = new MerchantRegisterDTO();
    dto.setName("uqpay-api-demo-new-sdk");
    dto.setAbbr("uadTest");
    dto.setRegEmail((Math.random()*9+1)*100000 + "@uqpay.test");
    dto.setCompanyName("test merchant");
    dto.setRegNo("123123123");
    dto.setCity("Singapore");
    dto.setProvince("Singapore");
    dto.setCountry("SG");
    dto.setZipCode("419529");
    dto.setAddressLine1("Address Line 1");
    dto.setBusinessScope("Test");
    dto.setMcc("0742");
    dto.setWebsite("www.uqpay.tech");
    dto.setContact("+65 69096616");
    dto.setMobile("+86 0571-88739581");
    dto.setEmail("business@uqpay.com");

    try {
      BaseAppgateResult result = merchant.register(dto);
      assertNotNull(result);
      assertTrue(result.getMerchantId() > 0);
      assertEquals(result.getAgentId(), 1005393);
    } catch (UqpayRSAException | UqpayResultVerifyException | IOException | UqpayPayFailException e) {
      e.printStackTrace();
    }
  }
}