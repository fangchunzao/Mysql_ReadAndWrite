package com.aop;

import com.config.DataSourceContextHolder;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.PriorityOrdered;
import org.springframework.stereotype.Component;

/**
 *  需要Aop在事务之前调用，AOP提前配置好数据源再进行事务
 *  @EnableTransactionManagement(order = 10) 启动器配置
 *  AOP 实现 PriorityOrdered 接口
 *  service注解控制读或写数据源 如果注解放在dao层 导致数据源设置错误
 */
@Aspect
@EnableAspectJAutoProxy(exposeProxy=true,proxyTargetClass=true)
@Component
public class DataSourceServiceAop  implements PriorityOrdered {

    private static Logger log = LoggerFactory.getLogger(DataSourceServiceAop.class);

    @Before("(@annotation(com.anno.DataSourceRead))")
    public void setWriteDataSourceType() {
        DataSourceContextHolder.read();
    }

    @Before("(@annotation(com.anno.DataSourceWrite))")
    public void setReadDataSourceType() {
        DataSourceContextHolder.write();
    }

    /**
     * 值越小，越优先执行  要优于事务的执行 在启动类中加上了@EnableTransactionManagement(order = 10)
     * @return
     */
    @Override
    public int getOrder() {
        return 1;
    }
}
