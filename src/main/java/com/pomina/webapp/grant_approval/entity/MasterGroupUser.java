package com.pomina.webapp.grant_approval.entity;

import com.pomina.common.model.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MasterGroupUser extends BaseEntity {
    private Integer masterGroupUserId;
    private String masterGroupUserCode;
    private String masterGroupUserName;
    private String masterGroupUserRole;
    private Byte masterGroupUserOrder;
    private String masterGroupUserDescription;
    private String userId;
}
