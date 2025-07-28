package com.pomina.appbaohanh.user_managerment.service;

import com.pomina.appbaohanh.user_managerment.dto.request.UserRequestDto;
import com.pomina.appbaohanh.user_managerment.dto.response.UserResponseDto;
import com.pomina.appbaohanh.common.service.BaseService;

public interface UserService extends BaseService<UserRequestDto, UserResponseDto, Integer> {
}
