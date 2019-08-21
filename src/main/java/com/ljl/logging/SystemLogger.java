package com.ljl.logging;

import com.ljl.constant.GlobalConstant;
import com.ljl.enums.LoggerEnum;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author lvjunlong
 * @date 2019/8/21 下午3:04
 */
public class SystemLogger {

    private static ConcurrentHashMap<LoggerEnum, Logger> logMaps = new ConcurrentHashMap();
    private static Logger GWS_LOGGER;
    private static Logger ACCESS_LOGGER;

    public SystemLogger() {
    }

    public static Logger getLogger(LoggerEnum loggerType) {
        Logger logger = (Logger)logMaps.get(loggerType);
        return null != logger ? logger : GWS_LOGGER;
    }

    public static Logger getLogger() {
        return GWS_LOGGER;
    }

    public static void info(LoggerEnum loggerType, String msg, Object... args) {
        Logger logger = getLogger(loggerType);
        if (!ACCESS_LOGGER.equals(logger)) {
            msg = addImportantToLog(msg);
        }

        logger.info(msg, args);
    }

    public static void info(String msg, Object... args) {
        Logger logger = getLogger();
        if (!ACCESS_LOGGER.equals(logger)) {
            msg = addImportantToLog(msg);
        }

        logger.info(msg, args);
    }

    public static void debug(String msg, Object... args) {
        Logger logger = getLogger();
        if (!ACCESS_LOGGER.equals(logger)) {
            msg = addImportantToLog(msg);
        }

        logger.debug(msg, args);
    }

    public static void debug(LoggerEnum loggerType, String msg, Object... args) {
        Logger logger = getLogger(loggerType);
        if (!ACCESS_LOGGER.equals(logger)) {
            msg = addImportantToLog(format(msg, args));
        }

        logger.debug(msg);
    }

    public static void error(String msg, Object... args) {
        Logger logger = getLogger();
        if (!ACCESS_LOGGER.equals(logger)) {
            msg = addImportantToLog(msg);
        }

        logger.error(msg, args);
    }

    public static void error(Throwable throwable, String msg, Object... args) {
        Logger logger = getLogger();
        if (!ACCESS_LOGGER.equals(logger)) {
            msg = addImportantToLog(format(msg, args));
        }

        logger.error(msg, throwable);
    }

    private static String addImportantToLog(String msg) {
        AccessLog accessLog = GlobalConstant.accessLog.get();
        String action = null != accessLog ? accessLog.getAction() : "";
        String sid = null != accessLog ? accessLog.getSid() : "";
        String uid = null != accessLog ? accessLog.getUid() : "";
        StringBuilder sb = new StringBuilder();
        sb.append("msg=").append(msg).append("===>");
        sb.append("action=").append(action).append("`");
        sb.append("uid=").append(uid).append("`");
        sb.append("sid=").append(sid).append("`");
        return sb.toString();
    }

    private static String format(String format, Object... args) {
        return args != null && args.length > 0 ? String.format(format, args) : format;
    }

    static {
        GWS_LOGGER = LogManager.getLogger(LoggerEnum.GWS.getLogerName());
        ACCESS_LOGGER = LogManager.getLogger(LoggerEnum.ACCESSTRACE.getLogerName());
        logMaps.put(LoggerEnum.GWS, GWS_LOGGER);
        logMaps.put(LoggerEnum.ACCESSTRACE, ACCESS_LOGGER);
        logMaps.put(LoggerEnum.SQLTRACE, LogManager.getLogger(LoggerEnum.SQLTRACE.getLogerName()));
    }
}
