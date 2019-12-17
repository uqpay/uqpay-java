package com.uqpay.sdk.hostui.bean;

import com.uqpay.sdk.operation.bean.result.BaseAppgateResult;

public class HostPreInitResult extends BaseAppgateResult {
    private static final long serialVersionUID = 7060114935180656115L;
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
