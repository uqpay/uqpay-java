package com.uqpay.sdk.dto.result.appgate;

public class HostPreInitResult extends BaseAppgateResult {
    private static final long serialVersionUID = 7060114935180656115L;
    private String uuid;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
