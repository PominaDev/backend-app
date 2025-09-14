package com.pomina.security.service.impl;

import com.pomina.common.exception.AppException;
import com.pomina.common.exception.ErrorCode;
import com.pomina.common.utils.AuditUtil;
import com.pomina.commonservices.location.dto.request.LocationRequestDto;
import com.pomina.commonservices.location.dto.response.LocationResponseDto;
import com.pomina.commonservices.location.service.LocationService;
import com.pomina.security.config.JwtAuthentication;
import com.pomina.security.mapper.SysUserMapper;
import com.pomina.security.model.SysUser;
import com.pomina.security.service.SysUserService;
import com.pomina.security.sysmodel.RegisterRequest;
import com.pomina.security.sysmodel.RegisterResponse;
import com.pomina.webapp.master_location_managerment.entity.MasterLocation;
import com.pomina.webapp.master_location_managerment.mapper.MasterLocationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SysUserServiceImpl implements SysUserService {

    private final SysUserMapper userMapper;

    private final PasswordEncoder passwordEncoder;

    private final LocationService locationService;

    private final MasterLocationMapper masterLocationMapper;


    @Override
    public Optional<SysUser> findByUserName(String userName) {
        SysUser userLogin = userMapper.getUserLogin(userName);
        if (Objects.nonNull(userLogin)) return Optional.of(userLogin);
        return Optional.empty();
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public RegisterResponse registerUser(RegisterRequest registerRequest) {
        // Kiểm tra user đã tồn tại chưa
        SysUser existingUser = userMapper.findByUserNameAndPhoneNumber(
                registerRequest.getPhoneNumber()
        );
        if (existingUser != null) {
            throw new AppException(ErrorCode.USER_EXISTED);
        }

        // Mã hóa password
        String encodedPassword = passwordEncoder.encode(registerRequest.getPassword());
        if (encodedPassword == null || encodedPassword.isEmpty()) {
            throw new AppException(ErrorCode.FAILED_ENCODED_PASSWORD);
        }

        // Tạo mới SysUser (chưa có location)
        SysUser user = new SysUser();
        user.setUsername(registerRequest.getUsername());
        user.setPhoneNumber(registerRequest.getPhoneNumber());
        user.setPassword(encodedPassword);
        user.setHoVaTen(registerRequest.getHoVaTen());
        user.setMaSoThue(registerRequest.getMaSoThue());
        user.setRoleId(registerRequest.getRoleId() != null ? registerRequest.getRoleId() : 7);
        user.setIsActive(true);

        AuditUtil.insert(user);

        // Insert để lấy được userId
        userMapper.insert(user); // sau insert thì user.getUserId() mới có giá trị

        // Gán userId cho location request
        LocationRequestDto locationRequest = registerRequest.getLocation();
        locationRequest.setUserId(user.getUserId());

        // Gọi service để tạo location
        LocationResponseDto locationResponseDto = locationService.registerLocation(locationRequest);
        if (locationResponseDto == null) {
            throw new AppException(ErrorCode.LOCATION_NOT_FOUND);
        }

        // Tìm masterLocation từ city
        String city = locationResponseDto.getCity();
        MasterLocation masterLocation = masterLocationMapper.findByOldName(city);
        if (masterLocation == null || masterLocation.getMasterLocationCode() == null) {
            throw new AppException(ErrorCode.LOCATION_NOT_FOUND);
        }

        // Cập nhật lại masterLocationCode vào user (vì trước đó chưa có)
        user.setMasterLocationCode(masterLocation.getMasterLocationCode());
        AuditUtil.update(user);
        userMapper.update(user); // cập nhật lại masterLocationCode cho user

        // Trả response
        return RegisterResponse.builder()
                .username(user.getUsername())
                .phoneNumber(user.getPhoneNumber())
                .hoVaTen(user.getHoVaTen())
                .maSoThue(user.getMaSoThue())
                .roleId(user.getRoleId().toString())
                .isActive(user.getIsActive())
                .location(locationResponseDto)
                .build();
    }
    @Override
    public String getCurUsername() {

        Integer userId = JwtAuthentication.getCurrentUserId();
        if (userId == null) return "anonymous";

        SysUser sysUser = userMapper.findByUserId(userId);
        if (sysUser == null) return "anonymous";

        return sysUser.getUsername();
    }
}
