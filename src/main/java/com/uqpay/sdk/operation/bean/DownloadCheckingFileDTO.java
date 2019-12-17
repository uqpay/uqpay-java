package com.uqpay.sdk.operation.bean;

import com.uqpay.sdk.operation.bean.BaseJsonRequestDTO;

import javax.validation.constraints.NotEmpty;

public class DownloadCheckingFileDTO extends BaseJsonRequestDTO {
  @NotEmpty
  private String beginDate; // use milliseconds
  @NotEmpty
  private String endDate; // use milliseconds;

  public String getBeginDate() {
    return beginDate;
  }

  public void setBeginDate(String beginDate) {
    this.beginDate = beginDate;
  }

  public String getEndDate() {
    return endDate;
  }

  public void setEndDate(String endDate) {
    this.endDate = endDate;
  }
}
