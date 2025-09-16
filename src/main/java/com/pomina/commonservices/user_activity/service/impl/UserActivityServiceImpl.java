package com.pomina.commonservices.user_activity.service.impl;

import com.pomina.commonservices.user_activity.entity.SysUserAction;
import com.pomina.commonservices.user_activity.entity.SysUserLogin;
import com.pomina.commonservices.user_activity.mapper.SysUserActionMapper;
import com.pomina.commonservices.user_activity.mapper.SysUserLoginMapper;
import com.pomina.commonservices.user_activity.service.UserActivityService;
import com.pomina.commonservices.user_activity.utils.RequestUtils;
import com.pomina.commonservices.user_activity.utils.UAParserUtils;
import com.pomina.security.config.JwtAuthentication;
import com.pomina.security.service.SysUserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserActivityServiceImpl implements UserActivityService {

    private final SysUserActionMapper sysUserActionMapper;

    private final SysUserLoginMapper sysUserLoginMapper;

    private final SysUserService sysUserService;

    @Async
    @Override
    public void logUserLogin(SysUserLogin userLogin) {
        sysUserLoginMapper.insert(userLogin);
    }

    @Async
    @Override
    public void logUserAction(HttpServletRequest request, String action) {

        SysUserAction userAction = SysUserAction.builder()
                .userId(JwtAuthentication.getCurrentUserId())
                .username(sysUserService.getCurUsername())
                .sessionId(request.getSession().getId())
                .action(action)
                .requestUrl(request.getRequestURI())
                .requestMethod(request.getMethod())
                .requestParams(RequestUtils.extractRequestParams(request))
                .build();

        // UA parsing
        String userAgent = request.getHeader("User-Agent");
        if (userAgent != null) {
            String ua = UAParserUtils.parse(userAgent);
            userAction.setNote(ua);
        }

        sysUserActionMapper.insert(userAction);
    }
}
