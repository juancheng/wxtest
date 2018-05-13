package com.hym.wxtest.enums;

import java.util.concurrent.TimeUnit;

/**
 * 过期时间常量类 单位s
 */
public enum  ExpireTimeEnum {

    WX_ACCESS_TOKEN(7200L, TimeUnit.SECONDS),
    REDIS_SAVE_TIME(15L, TimeUnit.DAYS);

    //  成员变量
    private Long time;
    private TimeUnit timeUnit;

    ExpireTimeEnum(Long time, TimeUnit timeUnit) {
        this.time = time;
        this.timeUnit = timeUnit;
    }

    public Long getTime() {
        return time;
    }

    public TimeUnit getTimeUnit() {
        return timeUnit;
    }
}
