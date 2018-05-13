package com.hym.wxtest.enums;

/**
 * 微信相关信息的常量类
 */
public enum WxEnum {
    ACCESS_TOKEN_FAIL(1001, "access_token 获取失败"),
    ACCESS_TOKEN_SUCCESS(200, "access_token 获取成功");

    private Integer code;
    private String message;

    WxEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
