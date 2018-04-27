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
}
