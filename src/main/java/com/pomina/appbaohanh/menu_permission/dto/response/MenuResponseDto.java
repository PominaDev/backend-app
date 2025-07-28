package com.pomina.appbaohanh.menu_permission.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MenuResponseDto {
    private Integer masterMenuId;
    private String masterMenuName;
    private String path;
    private String icon;
    private Boolean isParent;
    private Byte orderIndex;
    private Integer parentId;
    private Boolean isDeleted;
    private String noted;
}
