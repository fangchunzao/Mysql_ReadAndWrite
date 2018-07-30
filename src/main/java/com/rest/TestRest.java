package com.rest;

import com.config.DataBaseConfiguration;
import com.mapper.TestMapper;
import com.service.impl.TestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 */
@RestController
@RequestMapping("/test")
public class TestRest {

    private static Logger log = LoggerFactory.getLogger(DataBaseConfiguration.class);

    @Autowired
    private TestService testService;

    @RequestMapping(value = "/read",method = RequestMethod.GET)
    public String read() {
        return testService.readImpl().toString();
    }

    @RequestMapping(value = "/write",method = RequestMethod.GET)
    public String write() {
        return testService.writeImpl().toString();
    }

    @RequestMapping(value = "/testTransaction",method = RequestMethod.GET)
    public String testTransaction() {

        testService.testTransaction();
        return "success";
    }
}
