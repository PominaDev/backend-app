package com.pomina.commonservices.user_activity.service;

import com.pomina.commonservices.user_activity.entity.SysUserLogin;
import jakarta.servlet.http.HttpServletRequest;

public interface UserActivityService {

    void logUserLogin(SysUserLogin userLogin);

    void logUserAction(HttpServletRequest request, String action);
}
