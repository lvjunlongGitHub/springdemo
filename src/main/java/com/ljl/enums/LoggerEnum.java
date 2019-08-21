package com.ljl.enums;

/**
 * @author lvjunlong
 * @date 2019/8/21 下午3:08
 */
public enum LoggerEnum {

    GWS("GWS"),
    SQLTRACE("SQLTRACE"),
    ACCESSTRACE("ACCESSTRACE");

    private String logerName;

    private LoggerEnum(String logerName) {
        this.logerName = logerName;
    }

    public String getLogerName() {
        return this.logerName;
    }
}
