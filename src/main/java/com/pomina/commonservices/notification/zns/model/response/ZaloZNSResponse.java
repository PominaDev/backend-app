package com.pomina.commonservices.notification.zns.model.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ZaloZNSResponse {

    private int error;

    private String message;

    private Data data;
}
