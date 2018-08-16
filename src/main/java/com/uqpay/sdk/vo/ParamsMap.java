package com.uqpay.sdk.vo;

import java.io.Serializable;
import java.util.Map;

/**
 * <p>ParamsMap interface.</p>
 *
 * @author zhengwei
 * @version $Id: $Id
 */
public interface ParamsMap extends Serializable {
  /**
   * <p>getParamsMap.</p>
   *
   * @return a {@link java.util.Map} object.
   */
  Map<String, String> getParamsMap();
}
