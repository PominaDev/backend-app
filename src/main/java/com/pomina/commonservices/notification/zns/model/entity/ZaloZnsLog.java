package com.pomina.commonservices.notification.zns.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ZaloZnsLog {

    private Long id;

    private String accessToken;

    private String phone;

    private String templateId;

    private String templateData;

    private String trackingId;

    private Integer error;

    private String message;

    private String msgId;

    private String sentTime;

    private String dailyQuota;

    private String remainingQuota;
}
