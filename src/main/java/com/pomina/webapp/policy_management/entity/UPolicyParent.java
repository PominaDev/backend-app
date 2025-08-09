package com.pomina.webapp.policy_management.entity;

import com.pomina.common.model.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UPolicyParent extends BaseEntity {
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
