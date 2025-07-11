package com.pomina.erpapp.systemconfigsecurity.service.impl;

import com.pomina.erpapp.systemconfigsecurity.mapper.SysUserMapper;
import com.pomina.erpapp.systemconfigsecurity.model.SysUser;
import com.pomina.erpapp.systemconfigsecurity.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    @Autowired
    private SysUserMapper userMapper;

    @Override
    public Optional<SysUser> findByUserName(String userName) {
        SysUser userLogin = userMapper.getUserLogin(userName);
        if (Objects.nonNull(userLogin)) return Optional.of(userLogin);
        return Optional.empty();
    }

}
