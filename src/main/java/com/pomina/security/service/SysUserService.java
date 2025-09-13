package com.pomina.security.service;

import com.pomina.security.model.SysUser;
import com.pomina.security.sysmodel.RegisterRequest;
import com.pomina.security.sysmodel.RegisterResponse;

import java.util.Optional;

public interface SysUserService {
    Optional<SysUser> findByUserName(String userName);

    RegisterResponse registerUser(RegisterRequest registerRequest);

    String getCurUsername();
}
