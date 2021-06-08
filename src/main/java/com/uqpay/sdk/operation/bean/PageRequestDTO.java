package com.uqpay.sdk.operation.bean;

import com.uqpay.sdk.operation.bean.BaseJsonRequestDTO;

import jakarta.validation.constraints.Min;

public class PageRequestDTO extends BaseJsonRequestDTO {
  private static final long serialVersionUID = -6469936701014264919L;
  @Min(1)
  private int page = 1; // Current Page
  @Min(20)
  private int pageSize = 20;
  private boolean usePage = false;

  public int getPage() {
    return page;
  }

  public void setPage(int page) {
    this.page = page;
  }

  public int getPageSize() {
    return pageSize;
  }

  public void setPageSize(int pageSize) {
    this.pageSize = pageSize;
  }

  public boolean isUsePage() {
    return usePage;
  }

  public void setUsePage(boolean usePage) {
    this.usePage = usePage;
  }
}
