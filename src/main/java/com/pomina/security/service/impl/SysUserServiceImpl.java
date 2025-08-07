package com.pomina.security.service.impl;

import com.pomina.common.config.datasources.CustomDataSource;
import com.pomina.common.config.datasources.DataSourceType;
import com.pomina.common.exception.AppException;
import com.pomina.common.exception.ErrorCode;
import com.pomina.common.utils.AuditUtil;
import com.pomina.commonservices.location.dto.request.LocationRequestDto;
import com.pomina.commonservices.location.dto.response.LocationResponseDto;
import com.pomina.commonservices.location.service.LocationService;
import com.pomina.security.mapper.SysUserMapper;
import com.pomina.security.model.SysUser;
import com.pomina.security.service.SysUserService;
import com.pomina.security.sysmodel.RegisterRequest;
import com.pomina.security.sysmodel.RegisterResponse;
import com.pomina.webapp.master_location_managerment.entity.MasterLocation;
import com.pomina.webapp.master_location_managerment.mapper.MasterLocationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
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

    @CustomDataSource(DataSourceType.MASTER)
    @Override
    public Optional<SysUser> findByUserName(String userName) {
        SysUser userLogin = userMapper.getUserLogin(userName);
        if (Objects.nonNull(userLogin)) return Optional.of(userLogin);
        return Optional.empty();
    }

    @CustomDataSource(DataSourceType.MASTER)
    @Transactional
    @Override
    public RegisterResponse registerUser(RegisterRequest registerRequest) {
        // 1. Kiểm tra user đã tồn tại chưa
        SysUser existingUser = userMapper.findByUserNameAndPhoneNumber(
                registerRequest.getUsername(),
                registerRequest.getPhoneNumber()
        );
        if (existingUser != null) {
            throw new AppException(ErrorCode.USER_EXISTED);
        }

        // 2. Mã hóa password
        String encodedPassword = passwordEncoder.encode(registerRequest.getPassword());
        if (encodedPassword == null || encodedPassword.isEmpty()) {
            return null;
        }

        // 3. Tạo mới SysUser (chưa có location)
        SysUser user = new SysUser();
        user.setUsername(registerRequest.getUsername());
        user.setPhoneNumber(registerRequest.getPhoneNumber());
        user.setPassword(encodedPassword);
        user.setHoVaTen(registerRequest.getHoVaTen());
        user.setMaSoThue(registerRequest.getMaSoThue());
        user.setRoleId(registerRequest.getRoleId() != null ? registerRequest.getRoleId() : 7);
        user.setIsActive(registerRequest.getIsActive() != null ? registerRequest.getIsActive() : true);

        AuditUtil.insert(user);
        // Insert để lấy được userId
        userMapper.insert(user); // sau insert thì user.getUserId() mới có giá trị

        // 4. Gán userId cho location request
        LocationRequestDto locationRequest = registerRequest.getLocation();
        locationRequest.setUserId(user.getUserId());

        // 5. Gọi service để tạo location
        LocationResponseDto locationResponseDto = locationService.registerLocation(locationRequest);
        if (locationResponseDto == null) {
            throw new AppException(ErrorCode.LOCATION_NOT_FOUND);
        }

        // 6. Tìm masterLocation từ city
        String city = locationResponseDto.getCity();
        MasterLocation masterLocation = masterLocationMapper.findByOldName(city);
        if (masterLocation == null || masterLocation.getMasterLocationCode() == null) {
            throw new AppException(ErrorCode.LOCATION_NOT_FOUND);
        }

        // 7. Cập nhật lại masterLocationCode vào user (vì trước đó chưa có)
        user.setMasterLocationCode(masterLocation.getMasterLocationCode());
        AuditUtil.update(user);
        userMapper.update(user); // cập nhật lại masterLocationCode cho user

        // 8. Trả response
        RegisterResponse response = new RegisterResponse();
        response.setUsername(user.getUsername());
        response.setPhoneNumber(user.getPhoneNumber());
        response.setHoVaTen(user.getHoVaTen());
        response.setMaSoThue(user.getMaSoThue());
        response.setRoleId(user.getRoleId().toString());
        response.setIsActive(user.getIsActive());
        response.setLocation(locationResponseDto);

        return response;
    }
}
