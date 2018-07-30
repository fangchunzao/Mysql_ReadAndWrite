package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 *
 */

@SpringBootApplication
@EnableTransactionManagement(order = 10)
public class RunApplication  {

    public static void main(String[] args) {
        SpringApplication.run(RunApplication.class);
    }
}
