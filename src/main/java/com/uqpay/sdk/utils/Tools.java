package com.uqpay.sdk.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Tools {
  public static ObjectMapper mapper = new ObjectMapper();
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

  public static Map<String, Object> json2map(String jsonStr) throws IOException {
    return mapper.readValue(jsonStr, Map.class);
  }
}
