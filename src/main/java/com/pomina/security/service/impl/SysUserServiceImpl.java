package com.pomina.security.service.impl;

import com.pomina.common.config.datasources.CustomDataSource;
import com.pomina.common.config.datasources.DataSourceType;
import com.pomina.common.exception.AppException;
import com.pomina.common.exception.ErrorCode;
import com.pomina.common.utils.AuditUtil;
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

        // Check exist sysuser
        SysUser sysUser = userMapper.findByUserName(registerRequest.getUsername());
        if (sysUser != null) {
            throw new AppException(ErrorCode.USER_EXISTED);
        }

        // Encode password
        String encodedPassword = passwordEncoder.encode(registerRequest.getPassword());
        if (encodedPassword == null || encodedPassword.isEmpty()) {
            return null;
        }

        // Map to entity
        SysUser user = new SysUser();
        user.setUsername(registerRequest.getUsername());
        user.setPhoneNumber(registerRequest.getPhoneNumber());
        user.setPassword(encodedPassword);
        user.setHoVaTen(registerRequest.getHoVaTen());
        user.setMaSoThue(registerRequest.getMaSoThue());
        user.setRoleId(registerRequest.getRoleId());
        user.setIsActive(registerRequest.getIsActive());

        // Set UserID
        registerRequest.getLocation().setUserId(user.getUserId());
        LocationResponseDto locationResponseDto = locationService.registerLocation(registerRequest.getLocation());
        if (locationResponseDto == null) {
            throw new AppException(ErrorCode.LOCATION_NOT_FOUND);
        }

        String city = locationResponseDto.getCity();
        MasterLocation masterLocation = masterLocationMapper.findByOldName(city);
        if (masterLocation == null || masterLocation.getMasterLocationCode() == null) {
            throw new AppException(ErrorCode.LOCATION_NOT_FOUND);
        }
        user.setMasterLocationCode(masterLocation.getMasterLocationCode());

        AuditUtil.insert(user);

        // Save to database
        int resultInsert = userMapper.insert(user);
        if (resultInsert != 1) {
            throw new DataIntegrityViolationException("Insert user failed");
        }

        // Map to response
        RegisterResponse response = new RegisterResponse();
        response.setUsername(user.getUsername());
        response.setPhoneNumber(user.getPhoneNumber());
        response.setHoVaTen(user.getHoVaTen());
        response.setMaSoThue(user.getMaSoThue());
        response.setRoleId("USER"); //Default
        response.setIsActive(true); //Default
        response.setLocation(locationResponseDto);

        return response;
    }
}
