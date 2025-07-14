package com.pomina.erpapp.systemconfigsecurity.service.impl;

import com.pomina.erpapp.appbaohanh.common.config.datasource.CustomDataSource;
import com.pomina.erpapp.appbaohanh.common.config.datasource.DataSourceType;
import com.pomina.erpapp.systemconfigsecurity.mapper.SysUserMapper;
import com.pomina.erpapp.systemconfigsecurity.model.SysUser;
import com.pomina.erpapp.systemconfigsecurity.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final SysUserMapper userMapper;

    @CustomDataSource(DataSourceType.SLAVE)
    @Override
    public Optional<SysUser> findByUserName(String userName) {
        SysUser userLogin = userMapper.getUserLogin(userName);
        if (Objects.nonNull(userLogin)) return Optional.of(userLogin);
        return Optional.empty();
    }

}
