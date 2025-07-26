package com.pomina.erpapp.appbaohanh.web.menu_permission.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class MasterMenuResponseDto {
    private Integer masterMenuId;
    private String masterMenuName;
    private String path;
    private String icon;
    private Boolean isParent;
    private Byte orderIndex;
    private Integer parentId;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<MasterMenuResponseDto> menuItems;
}
