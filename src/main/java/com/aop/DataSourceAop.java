package com.aop;

import com.config.DataSourceContextHolder;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


/**
 *
 */

@Aspect
@Component
public class DataSourceAop {

    private static Logger log = LoggerFactory.getLogger(DataSourceAop.class);


}
