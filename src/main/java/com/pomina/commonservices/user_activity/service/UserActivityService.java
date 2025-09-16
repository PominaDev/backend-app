package com.pomina.commonservices.user_activity.service;

import com.pomina.commonservices.user_activity.model.dto.request.UserActionRequest;
import com.pomina.commonservices.user_activity.model.entity.SysUserLogin;

public interface UserActivityService {

    void logUserLogin(SysUserLogin userLogin);

    void logUserAction(UserActionRequest request);
}
