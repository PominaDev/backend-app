package com.pomina.commonservices.notification.zns.model.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Quota {

    private String dailyQuota;

    private String remainingQuota;
}
