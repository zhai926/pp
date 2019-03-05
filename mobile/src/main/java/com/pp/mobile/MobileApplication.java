package com.pp.mobile;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableTransactionManagement
@SpringBootApplication//(exclude = {DataSourceAutoConfiguration.cla})// 排除此类的autoconfig
@MapperScan({
        "com.pp.member.mapper",
        "com.pp.infrastructure.mapper",
        "com.pp.goods.mapper",
        "com.pp.video.mapper",
        "com.pp.article.mapper"
})
//@MapperScan("com.pp.*.mapper")
@ComponentScan(basePackages = {
        "com.pp.mobile.controller",
        "com.pp.mobile.interceptor",
        "com.pp.mobile.kaptcha",
        "com.pp.base.service",
        "com.pp.base.handler",
        "com.pp.member.service",
        "com.pp.video.service",
        "com.pp.goods.service",
        "com.pp.infrastructure.service",
        "com.pp.article.service"
})
//@ComponentScan(basePackages = {"com.pp.base", "com.pp.inventory"})
@EnableSwagger2
public class MobileApplication {

    public static void main(String[] args) {
        SpringApplication.run(MobileApplication.class, args);
    }
}
