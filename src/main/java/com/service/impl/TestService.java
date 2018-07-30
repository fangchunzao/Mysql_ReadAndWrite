package com.service.impl;

import com.anno.DataSourceRead;
import com.anno.DataSourceWrite;
import com.entity.People;
import com.mapper.TestMapper;
import com.service.TestServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 */
@Service
public class TestService implements TestServiceImpl {

    @Autowired
    private TestMapper testMapper;

    @DataSourceRead
    @Override
    public People readImpl() {
        return testMapper.read();
    }

    @DataSourceWrite
    @Override
    public People writeImpl() {
        return testMapper.write();
    }

    @DataSourceWrite
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void testTransaction() {
        People p = new People();
        p.setName("xxx");
        testMapper.testTransaction(p);
        //throw new RuntimeException("测试事务");
    }
}
