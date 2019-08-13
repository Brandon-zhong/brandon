package com.springbootmybatis.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

/**
 * @author brandon
 * Created on 2019-08-13.
 * desc:
 **/
@Configuration
@MapperScan(basePackages = "com.springbootmybatis.mapper.demo1")
public class DynamicDataSourceDemo1Config {

    @Bean("demo1")
    @ConfigurationProperties(prefix = "spring.datasource.demo1")
    public DataSource createDataSource() {
        return new DruidDataSourceBuilder().build();
    }

    @Bean(name = "demo1SqlSessionFactory")
    // 表示这个数据源是默认数据源
    @Primary
    public SqlSessionFactory test1SqlSessionFactory(@Qualifier("demo1") DataSource dataSource)
            throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(
                // 设置mybatis的xml所在位置
                new PathMatchingResourcePatternResolver().getResources("classpath*:com/springbootmybatis/mapper/demo1/*.xml"));
        return bean.getObject();
    }

    /*@Bean("demo1SqlSessionTemplate")
    // 表示这个数据源是默认数据源
    @Primary
    public SqlSessionTemplate test1sqlsessiontemplate(
            @Qualifier("test1SqlSessionFactory") SqlSessionFactory sessionfactory) {
        return new SqlSessionTemplate(sessionfactory);
    }*/

}
