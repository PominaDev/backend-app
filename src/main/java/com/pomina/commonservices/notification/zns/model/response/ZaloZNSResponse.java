package com.pomina.commonservices.notification.zns.model.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ZaloZNSResponse {

    private int error;

    private String message;

    private Data data;
}
