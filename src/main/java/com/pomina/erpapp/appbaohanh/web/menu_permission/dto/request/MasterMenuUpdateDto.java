package com.pomina.erpapp.appbaohanh.web.menu_permission.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MasterMenuUpdateDto {
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
