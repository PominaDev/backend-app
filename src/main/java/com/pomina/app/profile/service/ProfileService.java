package com.pomina.app.profile.service;

import com.pomina.app.profile.dto.request.UserProfileRequestDto;
import com.pomina.commonservices.user_management.dto.custom_mapper.UserProfile;

public interface ProfileService {

    UserProfile getProfile();

    Integer updateProfile(UserProfileRequestDto dto);
}
