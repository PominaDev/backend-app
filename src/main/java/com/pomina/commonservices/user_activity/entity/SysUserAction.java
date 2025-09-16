package com.pomina.commonservices.user_activity.entity;

import com.pomina.common.model.BaseEntity;
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
public class SysUserAction extends BaseEntity {

    private Integer id;

    private Integer userId;

    private String username;

    private String sessionId;

    private String action;

    private String requestUrl;

    private String requestMethod;

    private String requestParams;
}
