package com.pomina.app.profile.service.impl;

import com.pomina.app.profile.dto.request.UserProfileRequestDto;
import com.pomina.app.profile.service.ProfileService;
import com.pomina.common.exception.AppException;
import com.pomina.common.exception.ErrorCode;
import com.pomina.commonservices.user_management.dto.custom_mapper.UserProfile;
import com.pomina.commonservices.user_management.mapper.UserMapper;
import com.pomina.security.config.JwtAuthentication;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {

    private final UserMapper userMapper;

    @Override
    public UserProfile getProfile() {
        Integer userId = JwtAuthentication.getCurrentUserId();
        if (Objects.isNull(userId)) throw new AppException(ErrorCode.UNAUTHORIZED);

        return userMapper.getUserProfileByUserId(userId);

    }

    @Override
    @Transactional
    public Integer updateProfile(UserProfileRequestDto dto) {
        Integer userId = JwtAuthentication.getCurrentUserId();
        if (Objects.isNull(userId)) throw new AppException(ErrorCode.UNAUTHORIZED);

        return userMapper.updateProfile(userId, dto);
    }
}
