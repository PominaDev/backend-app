package com.pomina.erpapp.appbaohanh.web.menu_permission.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MasterMenuCreateDto {
    @NotNull(message = "Master menu name is mandatory")
    @Size(max = 255, message = "Master menu name must not exceed 255 characters")
    private String masterMenuName;

    @NotBlank(message = "Path is mandatory")
    @Size(max = 255, message = "Path must not exceed 255 characters")
    private String path;

    @Size(max = 100, message = "Icon must not exceed 100 characters")
    private String icon;

    private Boolean isParent;

    private Byte orderIndex;

    private Integer parentId;
}
