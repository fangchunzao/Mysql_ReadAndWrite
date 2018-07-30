package com.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;


/**
 *  第一步
 *  配置数据库的属性
 */
@Configuration
@PropertySource(value = {"classpath:application.yml"})
public class DataBaseConfiguration {

    @Value("${spring.datasource.type}")
    private Class<? extends DataSource> dataSourceType;

    private static Logger log = LoggerFactory.getLogger(DataBaseConfiguration.class);

    // 配置读库
    @Bean(name="writeDataSource")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource writeDataSource() {
        log.info("-------------------- writeDataSource init ---------------------");
        return DataSourceBuilder.create().type(dataSourceType).build();
    }
    // 配置写库1
    @Bean(name = "readDataSource1")
    @ConfigurationProperties(prefix = "spring.read1")
    public DataSource readDataSourceOne(){
        log.info("-------------------- readDataSource1 init ---------------------");
        return DataSourceBuilder.create().type(dataSourceType).build();
    }
    // 配置写库2
    @Bean(name = "readDataSource2")
    @ConfigurationProperties(prefix = "spring.read2")
    public DataSource readDataSourceTwo() {
        log.info("-------------------- readDataSource2 init ---------------------");
        return DataSourceBuilder.create().type(dataSourceType).build();
    }

    @Bean("readDataSources")
    public List<DataSource> readDataSources(){  // 将写库 配置信息都放入Bean readDataSources
        List<DataSource> dataSources=new ArrayList<>();
        dataSources.add(readDataSourceOne());
        dataSources.add(readDataSourceTwo());
        return dataSources;
    }

}
