package com.hym.wxtest.exception;

import com.hym.wxtest.enums.WxEnum;

/**
 * 自定义wx相关异常
 */
public class WxException extends RuntimeException {
    private Integer code;  //错误码

    public WxException(Integer code, String msg) {
        super(msg);
        this.code = code;
    }

    public WxException(WxEnum wxEnum) {
        super(wxEnum.getMessage());
        this.code = wxEnum.getCode();
    }

    public Integer getCode() {
        return code;
    }
}
