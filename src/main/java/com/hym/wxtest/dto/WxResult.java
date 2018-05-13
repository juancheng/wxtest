package com.hym.wxtest.dto;

import com.hym.wxtest.enums.WxEnum;
import com.hym.wxtest.exception.WxException;

/**
 * 返回后台获取微信相关信息的dto
 */
public class WxResult<T> extends BaseResult {

    public WxResult(WxException wx) {
        this.code = wx.getCode();
        this.message = wx.getMessage();
    }

    public WxResult(WxEnum wxEnum) {
        this.code = wxEnum.getCode();
        this.message = wxEnum.getMessage();
    }

    public WxResult(WxEnum wxEnum, T data) {
        this.code = wxEnum.getCode();
        this.message = wxEnum.getMessage();
        this.data = data;
    }

    public WxResult(Integer code, String data, String msg) {
        this.code = code;
        this.data = data;
        this.message = msg;
    }

    @Override
    public String toString() {
        return "WxResult{" +
                "code=" + code +
                ", data=" + data +
                ", message='" + message + '\'' +
                '}';
    }
}
