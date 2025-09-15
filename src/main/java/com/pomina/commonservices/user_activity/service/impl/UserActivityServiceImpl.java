package com.pomina.commonservices.user_activity.service.impl;

import com.pomina.commonservices.user_activity.mapper.SysUserActionMapper;
import com.pomina.commonservices.user_activity.mapper.SysUserLoginMapper;
import com.pomina.commonservices.user_activity.service.UserActivityService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserActivityServiceImpl implements UserActivityService {

    private final SysUserActionMapper sysUserActionMapper;

    private final SysUserLoginMapper sysUserLoginMapper;
}
