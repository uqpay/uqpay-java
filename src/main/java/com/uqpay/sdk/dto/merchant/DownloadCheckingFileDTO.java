package com.uqpay.sdk.dto.merchant;

import com.uqpay.sdk.dto.common.BaseJsonRequestDTO;

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
