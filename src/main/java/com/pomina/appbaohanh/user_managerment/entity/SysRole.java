package com.pomina.appbaohanh.user_managerment.entity;

import com.pomina.appbaohanh.common.model.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SysRole extends BaseEntity {

    private Integer id;

    private String roleName;

    private String description;
}