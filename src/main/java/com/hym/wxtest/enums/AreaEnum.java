package com.hym.wxtest.enums;

/**
 * Area相关的常量类
 */
public enum AreaEnum {
    SUCCESS(200, "成功"),
    SUCCESS_SAVE(200, "添加区域信息成功"),
    SUCCESS_UPDATE(200, "修改区域信息成功"),
    SUCCESS_DELETE(200, "删除区域信息成功"),
    FIND_DATA_NONE(200, "目前，没有相关数据"),
    MATCH_DATA_NONE(1002, "抱歉，没有匹配的数据"),
    FAIL_SAVE(1003, "添加区域信息失败"),
    FAIL_UPDATE(1004, "修改区域信息失败"),
    FAIL_DELETE(1005, "删除区域信息失败");

    private Integer code;
    private String message;

    AreaEnum(Integer code, String message) {
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
