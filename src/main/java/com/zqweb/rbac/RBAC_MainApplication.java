package com.zqweb.rbac;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

//扫描mapper
@MapperScan("com.zqweb.rbac.mapper")
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class RBAC_MainApplication {
    public static void main(String[] args) {
        SpringApplication.run(RBAC_MainApplication.class, args);
    }
}
