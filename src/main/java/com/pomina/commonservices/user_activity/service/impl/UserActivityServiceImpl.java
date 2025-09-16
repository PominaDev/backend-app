package com.pomina.commonservices.user_activity.service.impl;

import com.pomina.common.utils.AuditUtil;
import com.pomina.commonservices.user_activity.mapper.SysUserActionMapper;
import com.pomina.commonservices.user_activity.mapper.SysUserLoginMapper;
import com.pomina.commonservices.user_activity.model.dto.request.UserActionRequest;
import com.pomina.commonservices.user_activity.model.entity.SysUserAction;
import com.pomina.commonservices.user_activity.model.entity.SysUserLogin;
import com.pomina.commonservices.user_activity.service.UserActivityService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserActivityServiceImpl implements UserActivityService {

    private final SysUserActionMapper sysUserActionMapper;

    private final SysUserLoginMapper sysUserLoginMapper;

    @Async
    @Override
    public void logUserLogin(SysUserLogin userLogin) {
        sysUserLoginMapper.insert(userLogin);
    }

    @Async
    @Override
    public void logUserAction(UserActionRequest request) {

        SysUserAction userAction = SysUserAction.builder()
                .userId(request.getUserId())
                .username(request.getUsername())
                .sessionId(request.getSessionId())
                .action(request.getAction())
                .requestUrl(request.getRequestUrl())
                .requestMethod(request.getRequestMethod())
                .requestParams(request.getRequestParams())
                .build();

        AuditUtil.insert(userAction);
        sysUserActionMapper.insert(userAction);
    }
}
