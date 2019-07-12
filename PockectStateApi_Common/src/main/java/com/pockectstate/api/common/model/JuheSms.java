package com.pockectstate.api.common.model;

/**
 * @author:Yixi
 * @date:2019/7/9
 */
public class JuheSms {
    private Integer error_code;
    private String reason;

    public Integer getError_code() {
        return error_code;
    }

    public void setError_code(Integer error_code) {
        this.error_code = error_code;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
