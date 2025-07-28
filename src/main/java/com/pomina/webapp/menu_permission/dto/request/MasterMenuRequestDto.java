package com.pomina.webapp.menu_permission.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MasterMenuRequestDto {
    private Integer masterMenuId;
    private String masterMenuName;
    private String path;
    private String icon;
    private Boolean isParent;
    private Byte orderIndex;
    private Integer parentId;
    private String status;
    private String noted;
    private String createdBy;
    private String updatedBy;
    private Boolean isDeleted;
}
