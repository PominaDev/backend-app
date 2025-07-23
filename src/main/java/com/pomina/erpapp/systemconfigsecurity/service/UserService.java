package com.pomina.erpapp.systemconfigsecurity.service;

import com.pomina.erpapp.systemconfigsecurity.model.SysUser;
import com.pomina.erpapp.systemconfigsecurity.sysmodel.RegisterRequest;
import com.pomina.erpapp.systemconfigsecurity.sysmodel.RegisterResponse;

import java.util.Optional;

public interface UserService {
    public Optional<SysUser> findByUserName(String userName);

    RegisterResponse registerUser(RegisterRequest registerRequest);
}
