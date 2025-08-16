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
        "com.pomina.webapp.grant_approval.mapper",
        "com.pomina.webapp.user_role_managerment.mapper",
        "com.pomina.webapp.master_location_managerment.mapper",
        "com.pomina.webapp.statistic_user_active.by_role.mapper",
        "com.pomina.webapp.statistic_user_active.by_m_location.mapper",
        "com.pomina.webapp.statistic_user_active.by_u_location.mapper",
        "com.pomina.webapp.pricing_policy_management.mapper",
        "com.pomina.app.category.mapper",
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
