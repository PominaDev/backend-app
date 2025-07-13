package com.pomina.erpapp.appbaohanh.common.config.datasource;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DataSourceAspect {

    @Before(value = "@annotation(dataSource)")
    public void dataSourcePoint(DataSource dataSource) {
        DynamicDataSourceHolder.putDataSource(dataSource.value());
    }

    @After(value = "@annotation(dataSource)")
    public void clearContext() {
        DynamicDataSourceHolder.clearDataSource();
    }
}
