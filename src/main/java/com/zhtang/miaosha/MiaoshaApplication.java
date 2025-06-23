package com.zhtang.miaosha;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class MiaoshaApplication {

    public static void main(String[] args) {

        SpringApplication.run(MiaoshaApplication.class, args);
        log.info("秒杀系统开始启动...");
    }

}
