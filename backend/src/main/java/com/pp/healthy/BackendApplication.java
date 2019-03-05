package com.pp.healthy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableTransactionManagement
@SpringBootApplication//(exclude = {DataSourceAutoConfiguration.cla})// 排除此类的autoconfig
@MapperScan({
        "com.pp.infrastructure.mapper",
        "com.pp.article.mapper",
        "com.pp.employee.mapper",
        "com.pp.order.mapper",
        "com.pp.goods.mapper",
        "com.pp.member.mapper",
        "com.pp.video.mapper"
})
//@MapperScan("com.pp.*.mapper")
@ComponentScan(basePackages = {
        "com.pp.healthy.datasource",
        "com.pp.healthy.controller",
        "com.pp.healthy.interceptor",
        "com.pp.healthy.config",
        "com.pp.healthy.utils",
        "com.pp.base.service",
        "com.pp.base.handler",
        "com.pp.infrastructure.service",
        "com.pp.employee.service",
        "com.pp.article.service",
        "com.pp.member.service",
        "com.pp.goods.service",
        "com.pp.video.service",
        "com.pp.healthy.scheduling"
})
//@ComponentScan(basePackages = {"com.pp.base", "com.pp.inventory"})
@EnableSwagger2
@EnableScheduling // @EnableScheduling来开启定时任务
public class BackendApplication  extends WebMvcConfigurerAdapter {
//public class BackendApplication  extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }

    /*  使用外置tomcat 配置 extends SpringBootServletInitializer */
//    @Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
//        return application.sources(BackendApplication.class);
//    }

    /**
     * 处理跨域问题
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {

        registry.addMapping("/**")
                .allowCredentials(true)
                .allowedHeaders("*")
                .allowedOrigins("*")
                .allowedMethods("*");
    }

//    @Bean
//    private EmbeddedServletContainerCustomizer containerCustomizer(){
//        return new EmbeddedServletContainerCustomizer() {
//            @Override
//            public void customize(ConfigurableEmbeddedServletContainer container) {
//                container.setSessionTimeout(1800);//单位为S
//            }
//        };
//    }
}
