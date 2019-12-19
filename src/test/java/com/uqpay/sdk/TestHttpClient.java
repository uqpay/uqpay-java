package com.uqpay.sdk;

import com.uqpay.sdk.config.HttpClient;
import com.uqpay.sdk.config.ResourceTypeEnum;
import com.uqpay.sdk.utils.Tools;
import okhttp3.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestHttpClient implements HttpClient {
  public static OkHttpClient httpClient = new OkHttpClient.Builder().connectTimeout(10, TimeUnit.MINUTES).build();

  @Override
  public String post(Map<String, String> headers, String requestBody, String url) {
    Request request = new Request.Builder()
        .headers(Headers.of(headers))
        .post(RequestBody.create(MediaType.parse(headers.get("content-type")), requestBody))
        .url(url)
        .build();
    try {
      Response response = httpClient.newCall(request).execute();
      if (response.isSuccessful() && response.body() != null) {
        return response.body().string();
      } else {
        System.out.println(response.body().string());
      }
    } catch (Exception ignore) {

    }
    return null;
  }

  @Override
  public void download(Map<String, String> headers, String jsonBody, String url, ResourceTypeEnum resourceType) {
    Request request = new Request.Builder()
        .headers(Headers.of(headers))
        .post(RequestBody.create(MediaType.parse(headers.get("content-type")), jsonBody))
        .url(url)
        .build();
    Response response;
    String destPath = this.getClass().getResource("").getPath();
    try {
      response = httpClient.newCall(request).execute();
      if (response.isSuccessful()) {
        // write the file
        InputStream inputStream = null;
        byte[] buf = new byte[2048];
        int len = 0;
        FileOutputStream fos = null;
        try {
          String contentDisposition = response.header("Content-Disposition");
          String fileName = "checking.zip";
          if (contentDisposition != null && contentDisposition.length() > 0 && contentDisposition.indexOf("filename=") > 0) {
            Matcher matcher = Pattern.compile("filename=\"(.*?)\"").matcher(contentDisposition);
            if (matcher.find()) {
              fileName = matcher.group(1);
            }
          }
          inputStream = response.body().byteStream();
          File file = new File(destPath, fileName);
          fos = new FileOutputStream(file);
          while ((len = inputStream.read(buf)) != -1) {
            fos.write(buf, 0, len);
          }
          fos.flush();
        } finally {
          if (inputStream != null) {
            inputStream.close();
          }
          if (fos != null) {
            fos.close();
          }
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static String getTestUnionPayConsumerQRCode() {
    Request request = new Request.Builder()
        .post(RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), ""))
        .url("https://demo.uqpay.net/api/guest/union/qr")
        .build();
    try {
      Response response = httpClient.newCall(request).execute();
      if (response.isSuccessful() && response.body() != null) {
        Map<String,String> map = Tools.json2map(response.body().string());
        return map.get("data");
      }
      return "";
    } catch (IOException e) {
      e.printStackTrace();
    }
    return "";
  }
}
