package com.hym.wxtest.exception;

import com.hym.wxtest.enums.AreaEnum;

/**
 * 自定义Area异常类
 */
public class AreaException extends RuntimeException {

    private Integer code;  //错误码

    public AreaException(AreaEnum areaEnum) {
        super(areaEnum.getMessage());
        this.code = areaEnum.getCode();
    }

    public Integer getCode() {
        return code;
    }

}
