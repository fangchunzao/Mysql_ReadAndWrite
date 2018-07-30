package com.service;

import com.entity.People;

/**
 *
 */
public interface TestServiceImpl {

    People readImpl();

    People writeImpl();

    void testTransaction();
}
