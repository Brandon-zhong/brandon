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
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * @author brandon
 * Created on 2019-08-13.
 * desc:
 **/
@Configuration
@MapperScan(basePackages = "com.springbootmybatis.mapper.demo2", sqlSessionFactoryRef = "demo2SqlSessionFactory")
public class DynamicDataSourceDemo2Config {

    // 精确到 cluster 目录，以便跟其他数据源隔离
    static final String PACKAGE = "com.springbootmybatis.mapper.demo2";
    static final String MAPPER_LOCATION = "classpath:com/springbootmybatis/mapper/demo2/*.xml";

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
                new PathMatchingResourcePatternResolver().getResources(DynamicDataSourceDemo2Config.MAPPER_LOCATION));
        return bean.getObject();
    }

    // 创建该数据源的事务管理
    @Bean(name = "demo2TransactionManager")
    public DataSourceTransactionManager primaryTransactionManager(@Qualifier("demo2") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }


}
