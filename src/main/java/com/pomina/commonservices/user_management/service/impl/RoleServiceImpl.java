package com.pomina.commonservices.user_management.service.impl;

import com.pomina.common.config.datasources.CustomDataSource;
import com.pomina.common.config.datasources.DataSourceType;
import com.pomina.common.model.PageRequest;
import com.pomina.common.model.PageResponse;
import com.pomina.commonservices.user_management.converter.RoleConverter;
import com.pomina.commonservices.user_management.dto.request.RoleRequestDto;
import com.pomina.commonservices.user_management.dto.response.RoleResponseDto;
import com.pomina.commonservices.user_management.entity.SysRole;
import com.pomina.commonservices.user_management.mapper.RoleMapper;
import com.pomina.commonservices.user_management.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    // Dependency Injection
    private final RoleMapper roleMapper;

    private final RoleConverter roleConverter;

    public RoleServiceImpl(RoleMapper roleMapper, RoleConverter roleConverter) {
        this.roleMapper = roleMapper;
        this.roleConverter = roleConverter;
    }

    @CustomDataSource(DataSourceType.MASTER)
    @Override
    public int create(RoleRequestDto dto) {
        SysRole role = roleConverter.toEntity(dto);
        return roleMapper.insert(role);
    }

    @CustomDataSource(DataSourceType.MASTER)
    @Override
    public int update(Integer id, RoleRequestDto dto) {
        SysRole role = roleConverter.toEntity(dto);
        role.setId(id);
        return roleMapper.update(role);
    }

    @CustomDataSource(DataSourceType.SLAVE)
    @Override
    public RoleResponseDto getById(Integer id) {
        SysRole role = roleMapper.findById(id);
        return role != null ? roleConverter.toResponse(role) : null;
    }

    @CustomDataSource(DataSourceType.SLAVE)
    @Override
    public PageResponse<RoleResponseDto> search(PageRequest pageRequest) {
        List<SysRole> roleList = roleMapper.findAllPaged(pageRequest.getOffset(),
                pageRequest.getSize(),
                pageRequest);

        if (roleList == null || roleList.isEmpty()) {
            return PageResponse.empty(pageRequest.getPage(), pageRequest.getSize());
        }

        List<RoleResponseDto> roleResponse = roleConverter.toResponseList(roleList);

        int totalElements = roleMapper.countAll();

        return PageResponse.createPaged(roleResponse, pageRequest.getPage(), pageRequest.getSize(), totalElements);
    }

    @CustomDataSource(DataSourceType.MASTER)
    @Override
    public int delete(Integer id) {
        return roleMapper.deleteById(id);
    }

    @Override
    public List<RoleResponseDto> getAll() {
        List<SysRole> roleList = roleMapper.findAll();
        if (roleList != null) {
            return roleConverter.toResponseList(roleList);
        }
        return null;
    }
}
