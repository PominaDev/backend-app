package com.pomina.app.profile.controller;

import com.pomina.app.profile.service.ProfileService;
import com.pomina.common.constant.ApiConstants;
import com.pomina.common.handler.ApiResponse;
import com.pomina.common.handler.ResponseHandler;
import com.pomina.commonservices.user_management.dto.custom_mapper.UserProfile;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller xử lý các yêu cầu liên quan đến profile user.
 *
 * @author namnm
 * @version 1.0
 * @since 2025-08-04
 */
@RestController
@RequestMapping(ApiConstants.ApiProfile.BASE)
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;

    /**
     * Description: Lấy thông tin chi tiết người dùng hiện tại.
     * <p>
     * Endpoint: /api/v1/profiles
     *
     * @return {@link UserProfile} -
     * Thông tin chi tiết người dùng hiện tại
     */
    @GetMapping
    public ResponseEntity<ApiResponse<UserProfile>> getProfile() {
        return ResponseHandler.success(profileService.getProfile());
    }
}
