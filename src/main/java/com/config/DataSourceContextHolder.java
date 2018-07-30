package com.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *  第二步
 *  切换不同的数据源
 */
public class DataSourceContextHolder {

    private static Logger log = LoggerFactory.getLogger(DataSourceContextHolder.class);
    private static final ThreadLocal<String> local = new ThreadLocal<String>();
    public static ThreadLocal<String> getLocal() {
        return local;
    }

    /**
     * 读可能是多个库
     */
    public static void read() {
        local.set(DataSourceType.read.getType());
        log.info("数据库已切换到读库...");
    }
    /**
     * 写只有一个库
     */
    public static void write() {
        local.set(DataSourceType.write.getType());
        log.info("数据库已切换到写库...");
    }

    public static String getJdbcType() {
        return local.get();
    }


}
