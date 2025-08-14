package com.pomina.webapp.pricing_policy_management.entity;

import com.pomina.common.model.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChinhSachParent extends BaseEntity {
    private Integer uChinhSachParentId;
    private String uChinhSachParentType;
    private String uChinhSachParentCode;
    private String uChinhSachParentName;
    private Integer userId;
    private String masterGroupUserCode;
    private LocalDateTime uDayBegin;
    private LocalDateTime uDayEnd;
    private String uStatus;
    private String uDescription;
    private LocalDateTime dateUpload;
    private String deptUpload;
    private String userUpload;
}
