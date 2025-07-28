package com.pomina.commonservices.user.management.service;

import com.pomina.common.service.BaseService;
import com.pomina.commonservices.user.management.dto.request.UserRequestDto;
import com.pomina.commonservices.user.management.dto.response.UserResponseDto;

public interface UserService extends BaseService<UserRequestDto, UserResponseDto, Integer> {
}
