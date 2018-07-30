package com.mapper;

import com.anno.Master;
import com.anno.Read;
import com.entity.People;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 *
 */
@Mapper
@Component
public interface TestMapper {

    People read();

    People write();

    void testTransaction(People people);
}
