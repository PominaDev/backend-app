package com.pomina.webapp.policy_management.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UPolicyParentRequestDto{
    private Integer policyParentId;
    private String policyParentType;
    private String policyParentCode;
    private String policyParentName;
    private Integer userId;
    private String masterGroupUserCode;
    private LocalDateTime uDayBegin;
    private LocalDateTime uDayEnd;
    private String uStatus;
    private String uDescription;
}
