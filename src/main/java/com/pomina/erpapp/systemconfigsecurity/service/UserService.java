package com.pomina.erpapp.systemconfigsecurity.service;

import com.pomina.erpapp.systemconfigsecurity.model.SysUser;

import java.util.Optional;

public interface UserService {
    public Optional<SysUser> findByUserName(String userName);
}
