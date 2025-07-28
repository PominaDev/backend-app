package com.pomina.appbaohanh.common.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = {
        "com.pomina.appbaohanh.security.mapper",
        "com.pomina.appbaohanh.product_warranty_activation.mapper",
        "com.pomina.appbaohanh.user_managerment.mapper",
        "com.pomina.appbaohanh.location.mapper",
        "com.pomina.appbaohanh.menu_permission.mapper",
        "com.pomina.appbaohanh.notification.websocket.mapper",
})
public class MybatisConfig {

    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(
            @Qualifier("dataSource") DataSource dataSource) throws Exception {

        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);

         factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver()
             .getResources("classpath:/mybatis/mapper/**/*.xml"));

        return factoryBean.getObject();
    }
}
