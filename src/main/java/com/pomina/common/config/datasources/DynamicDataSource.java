package com.pomina.common.config.datasources;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DynamicDataSource extends AbstractRoutingDataSource {

    @Override
    protected String determineCurrentLookupKey() {
        if (DynamicDataSourceHolder.getDataSource() != null) {
            return DynamicDataSourceHolder.getDataSource();
        }
        return DataSourceType.MASTER.getType();
    }
}
