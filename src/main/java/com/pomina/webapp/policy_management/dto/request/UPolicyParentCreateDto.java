package com.pomina.webapp.policy_management.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
public class UPolicyParentCreateDto {

    @NotNull(message = "Policy parent type is mandatory")
    @Size(max = 50, message = "Policy parent type must not exceed 50 characters")
    private String policyParentType;

    @NotNull(message = "Policy parent code is mandatory")
    @Size(max = 255, message = "Policy parent code must not exceed 255 characters")
    private String policyParentCode;

    @NotNull(message = "Policy parent name is mandatory")
    @Size(max = 255, message = "Policy parent name must not exceed 255 characters")
    private String policyParentName;

    @NotNull(message = "Sys UserId is mandatory")
    private Integer userId;

    @NotNull(message = "Master group user code is mandatory")
    @Size(max = 255, message = "Master group user name must not exceed 255 characters")
    private String masterGroupUserCode;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @NotNull(message = "Day begin is mandatory")
    private LocalDateTime uDayBegin;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @NotNull(message = "Day end is mandatory")
    private LocalDateTime uDayEnd;

    @NotNull(message = "uStatus is mandatory")
    @Size(max = 50, message = "uStatus must not exceed 50 characters")
    private String uStatus;

    @NotNull(message = "uDescription is mandatory")
    @Size(max = 500, message = "uDescription must not exceed 500 characters")
    private String uDescription;
}
