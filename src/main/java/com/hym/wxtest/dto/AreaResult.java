package com.hym.wxtest.dto;

import com.hym.wxtest.enums.AreaEnum;
import com.hym.wxtest.exception.AreaException;

/**
 * 区域实体类返回用户操作的数据
 * @param <T>
 */
public class AreaResult<T> extends BaseResult<T> {

    public AreaResult(AreaException ae) {
        this.code = ae.getCode();
        this.message = ae.getMessage();
    }

    public AreaResult(AreaEnum ae) {
        this.code = ae.getCode();
        this.message = ae.getMessage();
    }

    public AreaResult(AreaEnum ae, T data) {
        this.code = ae.getCode();
        this.data = data;
        this.message = ae.getMessage();
    }

    @Override
    public String toString() {
        return "AreaResult{" +
                "code=" + code +
                ", data=" + data +
                ", message='" + message + '\'' +
                '}';
    }
}
