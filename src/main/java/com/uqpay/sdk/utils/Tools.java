package com.uqpay.sdk.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * <p>Tools class.</p>
 *
 * @author zhengwei
 * @version $Id: $Id
 */
public class Tools {
  /** Constant <code>mapper</code> */
  public static ObjectMapper mapper = new ObjectMapper();
  /**
   * <p>stringify.</p>
   *
   * @param paramsMap a {@link java.util.Map} object.
   * @param urlEncode a boolean.
   * @return a {@link java.lang.String} object.
   * @throws java.io.UnsupportedEncodingException if any.
   */
  public static String stringify(Map<String, String> paramsMap, boolean urlEncode) throws UnsupportedEncodingException {
    List<String> keys = new ArrayList<>(paramsMap.keySet());
    Collections.sort(keys);
    String queryString = "";
    for (int i = 0; i < keys.size(); i++) {
      String key = keys.get(i);
      String value = urlEncode ? URLEncoder.encode(paramsMap.get(key), "UTF-8") : paramsMap.get(key);
      if (i == keys.size() - 1) {
        queryString = queryString + key + "=" + value;
      } else {
        queryString = queryString + key + "=" + value + "&";
      }
    }
    return queryString;
  }

  /**
   * <p>json2map.</p>
   *
   * @param jsonStr a {@link java.lang.String} object.
   * @return a {@link java.util.Map} object.
   * @throws java.io.IOException if any.
   */
  public static Map<String, Object> json2map(String jsonStr) throws IOException {
    return mapper.readValue(jsonStr, Map.class);
  }
}
