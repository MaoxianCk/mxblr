package com.mxblr;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableTransactionManagement
@RestController
@MapperScan("com.mxblr.dao")
@EnableSwagger2
@SpringBootApplication(scanBasePackages = {"com.mxblr"})
public class MxblrApplication {
    public static void main(String[] args) {
        SpringApplication.run(MxblrApplication.class, args);
    }

}
