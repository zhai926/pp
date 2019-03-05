package com.pp.mobile.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

//@Configuration
//@MapperScan(basePackages = {"com.pp.employee.mapper", "com.pp.order.mapper"}, sqlSessionTemplateRef = "baseSqlSessionTemplate")
public class BaseDataSourceConfig {

//    @Bean(name = "baseDataSource")
//    @ConfigurationProperties(prefix = "spring.datasource.base")
//    @Primary
//    public DataSource setDataSource() {
//        //return DataSourceBuilder.create().build();
//        //return new DruidDataSource();
//        return DataSourceBuilder.create().type(DruidDataSource.class).build();
//    }
//
//    @Bean(name = "baseTransactionManager")
//    @Primary
//    public DataSourceTransactionManager setTransactionManager(@Qualifier("baseDataSource") DataSource dataSource) {
//        return new DataSourceTransactionManager(dataSource);
//    }
//
//    @Bean(name = "baseSqlSessionFactory")
//    @Primary
//    public SqlSessionFactory setSqlSessionFactory(@Qualifier("baseDataSource") DataSource dataSource) throws Exception {
//        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
//        bean.setDataSource(dataSource);
//        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:/mapping/*.xml"));//"classpath:mapper/base/*.xml"
//        return bean.getObject();
//    }
//
//    @Bean(name = "baseSqlSessionTemplate")
//    @Primary
//    public SqlSessionTemplate setSqlSessionTemplate(@Qualifier("baseSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
//        return new SqlSessionTemplate(sqlSessionFactory);
//    }
}
