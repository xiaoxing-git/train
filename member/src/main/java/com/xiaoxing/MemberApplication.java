package com.xiaoxing;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@MapperScan("com.xiaoxing.train.member.mapper")
public class MemberApplication {

    public static void main(String[] args) {
        final Logger logger = LoggerFactory.getLogger(MemberApplication.class);
        SpringApplication.run(MemberApplication.class, args);
        logger.info("启动成功！");
    }

}
