package com.pomina.appbaohanh.common.config.datasource;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DataSourceAspect {

    @Before(value = "@annotation(customDataSource)")
    public void dataSourcePoint(CustomDataSource customDataSource) {
        DynamicDataSourceHolder.putDataSource(customDataSource.value());
    }

    @After(value = "@annotation(dataSource)")
    public void clearContext() {
        DynamicDataSourceHolder.clearDataSource();
    }
}
