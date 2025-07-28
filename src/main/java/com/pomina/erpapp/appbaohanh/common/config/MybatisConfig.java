package com.pomina.erpapp.appbaohanh.common.config;

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
        "com.pomina.erpapp.systemconfigsecurity.mapper",
        "com.pomina.erpapp.appbaohanh.product_warranty_activation.mapper",
        "com.pomina.erpapp.appbaohanh.client_managerment.mapper",
        "com.pomina.erpapp.appbaohanh.location_validation.mapper",
        "com.pomina.erpapp.appbaohanh.web.menu_permission.mapper",

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
