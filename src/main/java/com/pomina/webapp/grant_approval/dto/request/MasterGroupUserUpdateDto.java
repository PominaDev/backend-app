package com.pomina.webapp.grant_approval.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MasterGroupUserUpdateDto {
    private Integer masterGroupUserId;
    private String masterGroupUserCode;
    private String masterGroupUserName;
    private String masterGroupUserRole;
    private Byte masterGroupUserOrder;
    private String masterGroupUserDescription;
    private String userId;
}
