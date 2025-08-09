package com.pomina.webapp.policy_management.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UPolicyParentRequestDto{
    private Integer policyParentId;
    private String policyParentType;
    private String policyParentCode;
    private String policyParentName;
    private Integer userId;
    private String masterGroupUserCode;
    private Date uDayBegin;
    private Date uDayEnd;
    private String uStatus;
    private String uDescription;
}
