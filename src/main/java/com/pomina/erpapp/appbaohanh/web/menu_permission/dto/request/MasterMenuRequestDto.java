package com.pomina.erpapp.appbaohanh.web.menu_permission.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MasterMenuRequestDto {

    private String masterMenuName;
    private String path;
    private String icon;
    private Boolean isParent;
    private Byte orderIndex;
    private Integer parentId;
}
