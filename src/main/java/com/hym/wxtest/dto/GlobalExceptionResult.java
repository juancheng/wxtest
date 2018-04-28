package com.hym.wxtest.dto;

/**
 * 返回数据格式的定义
 * {
 *     status: Integer,
 *     data: {},
 *     message: 'message'
 * }
 */
public class GlobalExceptionResult<T> extends BaseResult<T> {

    public GlobalExceptionResult(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public GlobalExceptionResult(Integer code, T data) {
        this.code = code;
        this.data = data;
    }

    public GlobalExceptionResult(Integer code, T data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }


    @Override
    public String toString() {
        return "GlobalExceptionResult{" +
                "code=" + code +
                ", data=" + data +
                ", message='" + message + '\'' +
                '}';
    }
}
