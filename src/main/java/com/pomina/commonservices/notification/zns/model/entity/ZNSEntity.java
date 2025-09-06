package com.pomina.commonservices.notification.zns.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ZNSEntity {
    private String from;

    private String toPhone;

    private String clientReqId;

    private Integer status;

    private String trackingId;

    private Integer errorCode;

    private String description;

    private Long requestTime;

    private Long responseTime;

    private Date createdDate;

    private Date updatedDate;
}
