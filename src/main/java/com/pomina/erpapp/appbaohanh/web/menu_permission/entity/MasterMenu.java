package com.pomina.erpapp.appbaohanh.web.menu_permission.entity;

import com.pomina.erpapp.appbaohanh.common.model.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MasterMenu extends BaseEntity {
    private Integer masterMenuId;
    private String masterMenuName;
    private String path;
    private String icon;
    private Boolean isParent;
    private Byte orderIndex;
    private Integer parentId;
}
