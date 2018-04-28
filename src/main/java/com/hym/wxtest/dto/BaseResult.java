package com.hym.wxtest.dto;

/**
 * 定义基本的返回数据格式
 * {
 *     status: Integer,
 *     data: {},
 *     message: 'message'
 * }
 * @param <T>
 */
public class BaseResult<T> {
    protected Integer code;
    protected T data;
    protected String message;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
