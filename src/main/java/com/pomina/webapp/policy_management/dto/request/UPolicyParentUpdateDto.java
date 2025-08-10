package com.pomina.webapp.policy_management.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UPolicyParentUpdateDto {
    private Integer policyParentId;
    private String policyParentType;
    private String policyParentCode;
    private String policyParentName;
    private Integer userId;
    private String masterGroupUserCode;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime uDayBegin;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime uDayEnd;
    private String uStatus;
    private String uDescription;
}
