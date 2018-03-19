package com.kaka.retrofitdemo.net.bean;

/**
 * Created by kaka on 2018/3/9.
 * email:375120706@qq.com
 */

public class BaseResponse {
    private String errorCode;
    private String errorMsg;

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
