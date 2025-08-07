package com.pomina.webapp.grant_approval.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.pomina.common.model.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class MasterGroupUserResponseDto{
    private Integer masterGroupUserId;
    private String masterGroupUserCode;
    private String masterGroupUserName;
    private String masterGroupUserRole;
    private Byte masterGroupUserOrder;
    private String masterGroupUserDescription;
    private String userId;
}
