package com.pomina.erpapp.appbaohanh.web.menu_permission.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuPermissionResponseDto {
    
    private String id;
    private String name;
    private String path;
    private String icon;
    private String parentId;
    private Integer orderIndex;
    private List<MenuPermissionResponseDto> children;
    
    // Permission fields
    private Boolean isFull;
    private Boolean isInsert;
    private Boolean isDelete;
    private Boolean isUpdate;
    private Boolean isRead;
    private Boolean isPrint;
    private Boolean isExport;
} 