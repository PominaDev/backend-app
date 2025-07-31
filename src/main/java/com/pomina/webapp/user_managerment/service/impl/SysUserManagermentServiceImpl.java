package com.pomina.webapp.user_managerment.service.impl;

import com.pomina.common.model.PageResponse;
import com.pomina.webapp.user_managerment.converter.SysUserManagermentConverter;
//import com.pomina.webapp.user_managerment.converter.SysUserRoleWebConverter;
import com.pomina.webapp.user_managerment.dto.request.SysUserRequestDto;
import com.pomina.webapp.user_managerment.dto.respone.SysUserResponeDto;
import com.pomina.webapp.user_managerment.entity.SysUser;
import com.pomina.webapp.user_managerment.mapper.SysUserManagermentMapper;
import com.pomina.webapp.user_managerment.service.SysUserManagermentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SysUserManagermentServiceImpl implements SysUserManagermentService {
    private final SysUserManagermentMapper sysUserManagermentMapper;
    private final SysUserManagermentConverter sysUserManagermentConverter;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Integer deleteById(Integer id) {
        if (!sysUserManagermentMapper.existsById(id)) {
            throw new IllegalArgumentException("User with id " + id + " does not exist");
        }

        return sysUserManagermentMapper.deleteById(id);
    }

    @Override
    public Boolean existsById(Integer id) {
        return null;
    }

    @Override
    public List<SysUserResponeDto> findAll() {
        List<SysUser> users = sysUserManagermentMapper.findAll();
        if (users == null || users.isEmpty()) {
             return new ArrayList<>();
        }
        return sysUserManagermentConverter.toResponseList(users);
    }

    @Override
    public PageResponse<SysUserResponeDto> findAllPaged(Integer limit, Integer offset) {
        List<SysUser> users = sysUserManagermentMapper.findAllPaged(limit, offset);
        int totalElements = this.countAll();

        if (users.isEmpty()) {
            return PageResponse.empty(offset / limit + 1, limit);
        }

        List<SysUserResponeDto> content = sysUserManagermentConverter.toResponseList(users);

        int page = offset / limit + 1;
        return PageResponse.createPaged(content, page, limit, totalElements);

    }

    @Override
    public SysUser findById(Integer id) {
        return null;
    }

    @Override
    public Integer insert(SysUserRequestDto sysUserRequestDto) {

        SysUser sysUser = sysUserManagermentConverter.toEntity(sysUserRequestDto);

        sysUser.setPassword(passwordEncoder.encode(sysUser.getPassword()));

        sysUserManagermentMapper.insert(sysUser);

        return sysUser.getUserId();
    }

    @Override
    public Integer softDeleteById(Integer id) {
        if (!sysUserManagermentMapper.existsById(id)) {
            throw new IllegalArgumentException("User with id " + id + " does not exist");
        }
        return sysUserManagermentMapper.softDeleteById(id);
    }

    @Override
    public Integer update(SysUserRequestDto sysUserRequestDto) {
        if(ObjectUtils.isEmpty(sysUserRequestDto.getUserId())) {
            throw new IllegalArgumentException("User ID must not be null for update");
        }

        SysUser sysUser = sysUserManagermentConverter.toEntity(sysUserRequestDto);

        sysUser.setPassword(passwordEncoder.encode(sysUser.getPassword()));

        return sysUserManagermentMapper.update(sysUser);
    }

    @Override
    public Integer countAll() {
        return sysUserManagermentMapper.countAll();
    }
}
