package com.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import java.util.concurrent.atomic.AtomicInteger;

/**
 *  第三步
 *  多数据源配置
 */
public class MyAbstractRoutingDataSource extends AbstractRoutingDataSource {

    private final int dataSourceNumber;

    private AtomicInteger count = new AtomicInteger(0);

    public MyAbstractRoutingDataSource(int dataSourceNumber) {
        this.dataSourceNumber = dataSourceNumber;
    }

    @Override
    protected Object determineCurrentLookupKey() {
        String typeKey = DataSourceContextHolder.getJdbcType();
        if (typeKey == null || "".equals(typeKey)) { // 如果没有指定使用库 默认使用读库
            return DataSourceType.write.getType();
        }

        if (typeKey.equals(DataSourceType.write.getType())) {  // 如果指定写库
            return DataSourceType.write.getType();
        }

        // 简单负载均衡  分别从读库中读取数据
        int number = count.getAndAdd(1);
        int lookupKey = number % dataSourceNumber;
        return new Integer(lookupKey);
    }
}
