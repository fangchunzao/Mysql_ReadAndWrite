package com.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  第四步
 *  配置Mybatis
 */
@Configuration
@ConditionalOnClass({EnableTransactionManagement.class})
@Import({ DataBaseConfiguration.class})
@MapperScan(basePackages={"com.mapper"})
public class MybatisConfiguration {

    @Value("${spring.datasource.type}")
    private Class<? extends DataSource> dataSourceType;

    @Value("${spring.datasource.readSize}")
    private String dataSourceSize;

    // 配置 sqlSessionFactory
    @Bean
    @ConditionalOnMissingBean
    public SqlSessionFactory sqlSessionFactory(ApplicationContext ac) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(roundRobinDataSourceProxy(ac));  // 设置多数据源
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:mappings/*.xml"));
       // sqlSessionFactoryBean.setTypeAliasesPackage("com.aop.writeAndRead.entity");
        sqlSessionFactoryBean.getObject().getConfiguration().setMapUnderscoreToCamelCase(true);
        return sqlSessionFactoryBean.getObject();
    }

    // 多数据源的配置
    @Bean
    public AbstractRoutingDataSource roundRobinDataSourceProxy(ApplicationContext ac) {
        int size = Integer.parseInt(dataSourceSize);
        // 通过MyAbstractRoutingDataSource 配置多数据源进行读写分离
        MyAbstractRoutingDataSource proxy = new MyAbstractRoutingDataSource(size);
        Map<Object, Object> targetDataSources = new HashMap<Object, Object>();
        //多个读数据库时
        DataSource writeDataSource = (DataSource)ac.getBean("writeDataSource"); // 全部的写库
        List<DataSource> readDataSources = (List<DataSource>)ac.getBean("readDataSources");  // 全部的读库
        for (int i = 0; i < size; i++) {
            targetDataSources.put(i, readDataSources.get(i));
        }
        proxy.setDefaultTargetDataSource(writeDataSource); // 设置写库
        proxy.setTargetDataSources(targetDataSources); // 设置读库
        return proxy;
    }

    //事务管理配置
    @Bean
    public PlatformTransactionManager annotationDrivenTransactionManager(ApplicationContext ac) {
        return new DataSourceTransactionManager((DataSource)ac.getBean("roundRobinDataSourceProxy"));
    }



}
