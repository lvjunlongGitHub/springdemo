package com.ljl.constant;

import com.ljl.logging.AccessLog;

/**
 * @author lvjunlong
 * @date 2019/8/21 下午3:07
 */
public class GlobalConstant {

    public static final String CON_QUOTE = "`";
    public static ThreadLocal<AccessLog> accessLog = new ThreadLocal();

    public GlobalConstant() {
    }
}
