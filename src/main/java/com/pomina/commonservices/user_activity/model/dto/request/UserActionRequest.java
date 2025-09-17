package com.pomina.commonservices.user_activity.model.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserActionRequest {

    private Integer userId;

    private String username;

    private String sessionId;

    private String action;

    private String requestUrl;

    private String requestMethod;

    private String requestParams;
}
