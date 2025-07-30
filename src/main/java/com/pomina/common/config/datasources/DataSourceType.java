package com.pomina.common.config.datasources;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DataSourceType {

    MASTER("master"),
    SLAVE("slave");

    private final String type;
}
