package com.uqpay.sdk.vo;

import java.io.Serializable;
import java.util.Map;

public interface ParamsMap extends Serializable {
  Map<String, String> getParamsMap();
}
