# UQPAY Payment JAVA library

This JAVA library provides integration access to the UQPAY Payment Gateway.
## Dependencies
JDK 1.8+

## Quick Start Example

### Installation
add maven dependencies
```xml
<dependency>
    <groupId>com.uqpay.sdk</groupId>
    <artifactId>uqpay-payment</artifactId>
    <version>5.8.7</version>
</dependency>
```

### Sample Usage

```java
/**
* For more usage example, please check the test folder;
**/
public class UQPAY {
    private UQPay uqPay;
    
    String mer_uq_pub_key = "The_UQPAY_Public_Key_Your_Downloaded_Content";
    String mer_prv_key = "Your_Private_Key_Content";
    
    public void main() {
          uqPay = UQPay
              .setting(MemberTypeEnum.MERCHANT, 1005004)
              .env(EnvEnum.TEST)
              .decipher(SignTypeEnum.RSA, mer_uq_pub_key, false)
              .encipher(SignTypeEnum.RSA, mer_prv_key, false)
              .httpClient(HttpClientImplByYourSelf.class);
          // generate Payment API
          Payment payment = new Payment(uqPay);
          // try bank card payment
          PayOrder payOrder = new PayOrder();
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
          ApiResponse<TransResult> result = payment.bankCard(payOrder);
          if (result.isSuccess()) {
            // do your business
          } else {
            // check the result.getCode() and result.getMessage()
          }
    }
}
```

## Documentation

 * [Official documentation](https://developer.uqpay.com/api/#/)

## License

See the LICENSE file.
