package com.springbootmybatis.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
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
@MapperScan(basePackages = "com.springbootmybatis.mapper.demo2", sqlSessionFactoryRef = "demo2SqlSessionFactory")
public class DynamicDataSourceDemo2Config {

    @Bean("demo2")
    @ConfigurationProperties(prefix = "spring.datasource.demo2")
    public DataSource createDataSource() {
        return new DruidDataSourceBuilder().build();
    }

    @Bean(name = "demo2SqlSessionFactory")
    public SqlSessionFactory test1SqlSessionFactory(@Qualifier("demo2") DataSource dataSource)
            throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(
                // 设置mybatis的xml所在位置
                new PathMatchingResourcePatternResolver().getResources("classpath*:com/springbootmybatis/mapper/demo2/*.xml"));
        return bean.getObject();
    }

    /*@Bean("demo2SqlSessionTemplate")
    // 表示这个数据源是默认数据源
    @Primary
    public SqlSessionTemplate test1sqlsessiontemplate(
            @Qualifier("test1SqlSessionFactory") SqlSessionFactory sessionfactory) {
        return new SqlSessionTemplate(sessionfactory);
    }*/

}
