package com.uqpay.sdk.dto.result.appgate;

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
