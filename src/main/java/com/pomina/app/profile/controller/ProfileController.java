package com.pomina.app.profile.controller;

import com.pomina.app.profile.dto.request.UserProfileRequestDto;
import com.pomina.app.profile.service.ProfileService;
import com.pomina.common.constant.ApiConstants;
import com.pomina.common.handler.ApiResponse;
import com.pomina.common.handler.ResponseHandler;
import com.pomina.commonservices.user_management.dto.custom_mapper.UserProfile;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiConstants.ApiProfile.BASE)
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;

    @GetMapping
    public ResponseEntity<ApiResponse<UserProfile>> getProfile() {
        return ResponseHandler.success(profileService.getProfile());
    }

//    @PutMapping
//    public ResponseEntity<ApiResponse<Integer>> editProfile(@Valid @RequestBody UserProfileRequestDto userProfileDto) {
//        return ResponseHandler.success(profileService.updateProfile(userProfileDto));
//    }
}
