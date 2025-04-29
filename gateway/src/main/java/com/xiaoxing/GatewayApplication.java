package com.xiaoxing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class GatewayApplication {
    public static void main(String[] args) {
        final Logger logger = LoggerFactory.getLogger(GatewayApplication.class);
        SpringApplication.run(GatewayApplication.class, args);
        logger.info("启动成功！");
    }

}
