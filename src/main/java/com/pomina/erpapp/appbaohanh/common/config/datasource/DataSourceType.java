package com.pomina.erpapp.appbaohanh.common.config.datasource;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DataSourceType {

    MASTER("master"),
    SLAVE("slave");

    private final String type;
}
