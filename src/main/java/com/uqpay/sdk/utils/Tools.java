package com.uqpay.sdk.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.Assert;

import java.io.IOException;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
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

  public static Map<String, String> json2map(String jsonStr) throws IOException {
    return mapper.readValue(jsonStr, Map.class);
  }

  public static <T> T json2Obj(String jsonStr, Class<T> clasz) throws IOException {
    return mapper.readValue(jsonStr, clasz);
  }

  public static final String capitalize(String string) {
    char[] cache = string.toCharArray();
    cache[0] -= 32;
    return String.valueOf(cache);
  }

  public static <K, V> String mapToJson(Map<K, V> map) {
    String jsonString;
    try {
      if (map == null) {
        return "";
      }
      jsonString = mapper.writeValueAsString(map);
    } catch (Exception ex) {
      return "";
    }
    return jsonString;
  }

  public static String objToJson(Object obj) throws IOException {
    StringWriter sw = new StringWriter();
    mapper.writeValue(sw, obj);
    return sw.toString();
  }

  public static List<Field> getAllFieldsList(final Class<?> clazz) {
    Assert.notNull(clazz, "The class must not be null");
    final List<Field> allFields = new ArrayList<>();
    Class<?> currentClass = clazz;
    while (currentClass != null) {
      final Field[] declaredFields = currentClass.getDeclaredFields();
      for (final Field field : declaredFields) {
        allFields.add(field);
      }
      currentClass = currentClass.getSuperclass();
    }
    return allFields;
  }
  public static Field[] getAllFields(final Class<?> clazz) {
    final List<Field> allFieldsList = getAllFieldsList(clazz);
    return allFieldsList.toArray(new Field[allFieldsList.size()]);
  }
  public static <T> T enumValueOf(Class<T> enumType, short value) {
    T[] values = enumType.getEnumConstants();
    for (T val: values) {
      if (value == ((HasValue) val).getValue()) {
        return val;
      }
    }
    return null;
  }
}
