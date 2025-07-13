package com.pomina.erpapp.appbaohanh.client_managerment.service.impl;

import com.pomina.erpapp.appbaohanh.client_managerment.converter.RoleConverter;
import com.pomina.erpapp.appbaohanh.client_managerment.dto.request.RoleRequestDto;
import com.pomina.erpapp.appbaohanh.client_managerment.dto.response.RoleResponseDto;
import com.pomina.erpapp.appbaohanh.client_managerment.entity.SysRole;
import com.pomina.erpapp.appbaohanh.client_managerment.mapper.ClientRoleMapper;
import com.pomina.erpapp.appbaohanh.client_managerment.service.ClientRoleService;
import com.pomina.erpapp.appbaohanh.common.config.datasource.DataSource;
import com.pomina.erpapp.appbaohanh.common.config.datasource.DataSourceType;
import com.pomina.erpapp.appbaohanh.common.model.PageRequest;
import com.pomina.erpapp.appbaohanh.common.model.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientRoleServiceImpl implements ClientRoleService {

    // Dependency Injection
    private final ClientRoleMapper clientRoleMapper;

    private final RoleConverter roleConverter;

    @DataSource(DataSourceType.MASTER)
    @Override
    public int create(RoleRequestDto dto) {
        SysRole role = roleConverter.toEntity(dto);
        return clientRoleMapper.insert(role);
    }

    @DataSource(DataSourceType.MASTER)
    @Override
    public int update(Integer id, RoleRequestDto dto) {
        SysRole role = roleConverter.toEntity(dto);
        role.setId(id);
        return clientRoleMapper.update(role);
    }

    @DataSource(DataSourceType.SLAVE)
    @Override
    public RoleResponseDto getById(Integer id) {
        SysRole role = clientRoleMapper.findById(id);
        return role != null ? roleConverter.toResponse(role) : null;
    }

    @DataSource(DataSourceType.SLAVE)
    @Override
    public PageResponse<RoleResponseDto> search(PageRequest pageRequest) {
        List<SysRole> roleList = clientRoleMapper.findAllPaged(pageRequest.getOffset(),
                pageRequest.getSize(),
                pageRequest);

        if (roleList == null || roleList.isEmpty()) {
            return PageResponse.empty(pageRequest.getPage(), pageRequest.getSize());
        }

        List<RoleResponseDto> roleResponse = roleConverter.toResponseList(roleList);

        int totalElements = clientRoleMapper.countAll();

        return PageResponse.createPaged(roleResponse, pageRequest.getPage(), pageRequest.getSize(), totalElements);
    }

    @DataSource(DataSourceType.MASTER)
    @Override
    public int delete(Integer id) {
        return clientRoleMapper.deleteById(id);
    }
}
