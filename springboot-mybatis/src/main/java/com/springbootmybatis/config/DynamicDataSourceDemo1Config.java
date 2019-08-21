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
import java.sql.SQLException;

/**
 * @author brandon
 * Created on 2019-08-13.
 * desc:
 **/
@Configuration
@MapperScan(basePackages = "com.springbootmybatis.mapper.demo1", sqlSessionFactoryRef = "demo1SqlSessionFactory")
public class DynamicDataSourceDemo1Config {

    // 精确到 cluster 目录，以便跟其他数据源隔离
    static final String PACKAGE = "com.springbootmybatis.mapper.demo1";
    static final String MAPPER_LOCATION = "classpath:com/springbootmybatis/mapper/demo1/*.xml";

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
                new PathMatchingResourcePatternResolver().getResources(DynamicDataSourceDemo1Config.MAPPER_LOCATION));
        return bean.getObject();
    }

    // 创建该数据源的事务管理
    @Primary
    @Bean(name = "demo1TransactionManager")
    public DataSourceTransactionManager primaryTransactionManager(@Qualifier("demo1") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

}
