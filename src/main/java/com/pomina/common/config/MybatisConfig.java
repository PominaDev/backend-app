package com.pomina.app.common.config;

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
        "com.pomina.app.security.mapper",
        "com.pomina.app.product_warranty_activation.mapper",
        "com.pomina.app.user_managerment.mapper",
        "com.pomina.app.location.mapper",
        "com.pomina.app.menu_permission.mapper",
        "com.pomina.app.notification.websocket.mapper",
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
