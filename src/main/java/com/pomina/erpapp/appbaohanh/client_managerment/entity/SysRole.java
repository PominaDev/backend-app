package com.pomina.erpapp.appbaohanh.client_managerment.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SysRole {
    private Integer id;
    private String roleName;
    private String description;
}