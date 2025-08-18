package com.pomina.commonservices.category.dto.custom_mapper;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryItem {

    @JsonIgnore
    private String nameGroup;

    private Integer idOrderType;

    private String nameType;
}
