package com.pomina.appbaohanh.security.service;

import com.pomina.appbaohanh.security.model.SysUser;
import com.pomina.appbaohanh.security.sysmodel.RegisterRequest;
import com.pomina.appbaohanh.security.sysmodel.RegisterResponse;

import java.util.Optional;

public interface SysUserService {
    Optional<SysUser> findByUserName(String userName);

    RegisterResponse registerUser(RegisterRequest registerRequest);
}
