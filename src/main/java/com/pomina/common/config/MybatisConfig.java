package com.pomina.common.config;

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
        "com.pomina.security.mapper",
        "com.pomina.commonservices.user_management.mapper",
        "com.pomina.commonservices.location.mapper",
        "com.pomina.commonservices.product_warranty_activation.mapper",
        "com.pomina.webapp.menu_permission.mapper",
        "com.pomina.webapp.notification_websocket.mapper",
        "com.pomina.webapp.user_managerment.mapper",
        "com.pomina.webapp.grant_approval.mapper"

})
public class MybatisConfig {

    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(
            @Qualifier("dataSource") DataSource dataSource) throws Exception {

        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);

         factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver()
                 .getResources("classpath:/mybatis/mapper/**/**/*.xml"));

        return factoryBean.getObject();
    }
}
