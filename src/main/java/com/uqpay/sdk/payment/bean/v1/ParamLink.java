package com.uqpay.sdk.payment.bean.v1;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface ParamLink {
  String value();
  String targetType() default "";
}
