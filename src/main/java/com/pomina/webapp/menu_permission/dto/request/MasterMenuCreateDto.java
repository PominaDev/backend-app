package com.pomina.app.menu_permission.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MasterMenuCreateDto {

//    @NotBlank(message = "Master menu id is mandatory")
//    private Integer masterMenuId;

    @NotNull(message = "Master menu name is mandatory")
    @Size(max = 255, message = "Master menu name must not exceed 255 characters")
    private String masterMenuName;

    @NotBlank(message = "Path is mandatory")
    @Size(max = 255, message = "Path must not exceed 255 characters")
    @Pattern(regexp = "^/.*", message = "Path must start with a '/' character")
    private String path;

    @Size(max = 100, message = "Icon must not exceed 100 characters")
    private String icon;

    @NotNull(message = "You must specify whether this menu is a parent or not")
    private Boolean isParent;

    @PositiveOrZero(message = "orderIndex must be 0 or positive")
    private Byte orderIndex;

    @Positive(message = "parentId must be a positive number")
    private Integer parentId;

    @Size(max = 255, message = "Status must not exceed 255 characters")
    private String status;

    @Size(max = 255, message = "Note must not exceed 255 characters")
    private String noted;

    private String createdBy;
}
