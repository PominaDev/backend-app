package com.pomina.erpapp.appbaohanh.common.config.datasource;

public class DynamicDataSourceHolder {

    private DynamicDataSourceHolder(){}

    private static final ThreadLocal<String> holder = new ThreadLocal<>();

    public static void putDataSource(DataSourceType dataSourceType) {
        holder.set(dataSourceType.getType());
    }

    public static String getDataSource() {
        return holder.get();
    }

    public static void clearDataSource() {
        holder.remove();
    }
}
