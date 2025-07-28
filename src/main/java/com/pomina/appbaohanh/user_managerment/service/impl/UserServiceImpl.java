package com.pomina.appbaohanh.user_managerment.service.impl;

import com.pomina.appbaohanh.user_managerment.converter.UserConverter;
import com.pomina.appbaohanh.user_managerment.dto.request.UserRequestDto;
import com.pomina.appbaohanh.user_managerment.dto.response.UserResponseDto;
import com.pomina.appbaohanh.user_managerment.entity.SysUser;
import com.pomina.appbaohanh.user_managerment.mapper.UserMapper;
import com.pomina.appbaohanh.user_managerment.service.UserService;
import com.pomina.appbaohanh.common.config.datasource.CustomDataSource;
import com.pomina.appbaohanh.common.config.datasource.DataSourceType;
import com.pomina.appbaohanh.common.model.PageRequest;
import com.pomina.appbaohanh.common.model.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    // Dependency injection
    private final UserMapper userMapper;

    private final UserConverter userConverter;

    @CustomDataSource(DataSourceType.MASTER)
    @Override
    public int create(UserRequestDto dto) {
        SysUser user = userConverter.toEntity(dto);
        return userMapper.insert(user);
    }

    @CustomDataSource(DataSourceType.MASTER)
    @Override
    public int update(Integer userId, UserRequestDto dto) {
        SysUser user = userConverter.toEntity(dto);
        user.setUserId(userId);
        return userMapper.update(user);
    }

    @CustomDataSource(DataSourceType.SLAVE)
    @Override
    public UserResponseDto getById(Integer userId) {
        SysUser user = userMapper.findById(userId);
        return user != null ? userConverter.toResponse(user) : null;
    }

    @CustomDataSource(DataSourceType.SLAVE)
    @Override
    public PageResponse<UserResponseDto> search(PageRequest pageRequest) {
        List<SysUser> userList = userMapper.findAllPaged(pageRequest.getOffset(),
                pageRequest.getSize(),
                pageRequest);

        if (userList == null || userList.isEmpty()) {
            return PageResponse.empty(pageRequest.getPage(), pageRequest.getSize());
        }

        List<UserResponseDto> userResponse = userConverter.toResponseList(userList);

        int totalElements = userMapper.countAll();

        return PageResponse.createPaged(userResponse, pageRequest.getPage(), pageRequest.getSize(), totalElements);
    }

    @CustomDataSource(DataSourceType.MASTER)
    @Override
    public int delete(Integer userId) {
        return userMapper.deleteById(userId);
    }
}
