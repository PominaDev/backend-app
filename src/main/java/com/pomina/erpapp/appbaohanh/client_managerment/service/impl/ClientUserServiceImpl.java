package com.pomina.erpapp.appbaohanh.client_managerment.service.impl;

import com.pomina.erpapp.appbaohanh.client_managerment.converter.UserConverter;
import com.pomina.erpapp.appbaohanh.client_managerment.dto.request.UserRequestDto;
import com.pomina.erpapp.appbaohanh.client_managerment.dto.response.UserResponseDto;
import com.pomina.erpapp.appbaohanh.client_managerment.entity.SysUser;
import com.pomina.erpapp.appbaohanh.client_managerment.mapper.ClientUserMapper;
import com.pomina.erpapp.appbaohanh.client_managerment.service.ClientUserService;
import com.pomina.erpapp.appbaohanh.common.config.datasource.CustomDataSource;
import com.pomina.erpapp.appbaohanh.common.config.datasource.DataSourceType;
import com.pomina.erpapp.appbaohanh.common.model.PageRequest;
import com.pomina.erpapp.appbaohanh.common.model.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientUserServiceImpl implements ClientUserService {

    // Dependency injection
    private final ClientUserMapper clientUserMapper;

    private final UserConverter userConverter;

    @CustomDataSource(DataSourceType.MASTER)
    @Override
    public int create(UserRequestDto dto) {
        SysUser user = userConverter.toEntity(dto);
        return clientUserMapper.insert(user);
    }

    @CustomDataSource(DataSourceType.MASTER)
    @Override
    public int update(Integer userId, UserRequestDto dto) {
        SysUser user = userConverter.toEntity(dto);
        user.setUserId(userId.longValue());
        return clientUserMapper.update(user);
    }

    @CustomDataSource(DataSourceType.SLAVE)
    @Override
    public UserResponseDto getById(Integer userId) {
        SysUser user = clientUserMapper.findById(userId);
        return user != null ? userConverter.toResponse(user) : null;
    }

    @CustomDataSource(DataSourceType.SLAVE)
    @Override
    public PageResponse<UserResponseDto> search(PageRequest pageRequest) {
        List<SysUser> userList = clientUserMapper.findAllPaged(pageRequest.getOffset(),
                pageRequest.getSize(),
                pageRequest);

        if (userList == null || userList.isEmpty()) {
            return PageResponse.empty(pageRequest.getPage(), pageRequest.getSize());
        }

        List<UserResponseDto> userResponse = userConverter.toResponseList(userList);

        int totalElements = clientUserMapper.countAll();

        return PageResponse.createPaged(userResponse, pageRequest.getPage(), pageRequest.getSize(), totalElements);
    }

    @CustomDataSource(DataSourceType.MASTER)
    @Override
    public int delete(Integer userId) {
        return clientUserMapper.deleteById(userId);
    }
}
