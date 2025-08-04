package com.pomina.webapp.user_managerment.service.impl;

import com.pomina.common.model.PageResponse;
import com.pomina.commonservices.location.entity.Location;
import com.pomina.commonservices.location.mapper.LocationMapper;
import com.pomina.webapp.user_managerment.converter.SysUserManagermentConverter;
import com.pomina.webapp.user_managerment.dto.request.SysUserRequestDto;
import com.pomina.webapp.user_managerment.dto.respone.SysUserResponeDto;
import com.pomina.webapp.user_managerment.entity.SysUser;
import com.pomina.webapp.user_managerment.mapper.SysUserManagermentMapper;
import com.pomina.webapp.user_managerment.service.SysUserManagermentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class SysUserManagermentServiceImpl implements SysUserManagermentService {
    private final SysUserManagermentMapper sysUserManagermentMapper;
    private final SysUserManagermentConverter sysUserManagermentConverter;
    private final PasswordEncoder passwordEncoder;
    private final LocationMapper locationWebMapper;

    @Override
    public Integer deleteById(Integer id) {
        if (Objects.isNull(sysUserManagermentMapper.isExistUser(id))) {
            throw new IllegalArgumentException("User with id " + id + " does not exist");
        }
        return sysUserManagermentMapper.deleteById(id);
    }

    @Override
    public Boolean existsById(Integer id) {
        return null;
    }

    @Override
    public List<SysUserResponeDto> findAll() {
        List<SysUser> users = sysUserManagermentMapper.findAll();
        if (users == null || users.isEmpty()) {
            return new ArrayList<>();
        }
        return sysUserManagermentConverter.toResponseList(users);
    }

    @Override
    public PageResponse<SysUserResponeDto> findAllPaged(Integer page, Integer size) {
        int offset = (page - 1) * size;

        List<SysUser> users = sysUserManagermentMapper.findAllPaged(size, offset);
        int totalElements = this.countAll();

        if (users.isEmpty()) {
            return PageResponse.empty(page, size);
        }

        List<SysUserResponeDto> content = sysUserManagermentConverter.toResponseList(users);

        return PageResponse.createPaged(content, page, size, totalElements);
    }

    @Override
    public SysUser findById(Integer id) {
        return null;
    }

    @Override
    public Integer insert(SysUserRequestDto sysUserRequestDto) {
        if (ObjectUtils.isEmpty(sysUserRequestDto.getPassword())) {
            throw new RuntimeException("Password must not be empty");
        }
        List<Integer> listUserId = sysUserManagermentMapper.getListUserIdByPhoneNumber(sysUserRequestDto.getPhoneNumber());

        if (ObjectUtils.isEmpty(listUserId)) {
            try {
                SysUser sysUser = sysUserManagermentConverter.toEntity(sysUserRequestDto);
                sysUser.setPassword(passwordEncoder.encode(sysUser.getPassword()));
                sysUser.setIsActive(true);
                sysUserManagermentMapper.insert(sysUser);

                // Insert Location
                Location ins = uLocationEntityFromSysUserDto(sysUserRequestDto);
                ins.setUserId(sysUserManagermentMapper.getListUserIdByPhoneNumber(sysUserRequestDto.getPhoneNumber()).getFirst());
                ins.setFullAddress(sysUserRequestDto.getAddress01() + ", " + sysUserRequestDto.getAddress02()
                        + ", " + sysUserRequestDto.getAddress03() + ", " + sysUserRequestDto.getAddress04() + ", " + sysUserRequestDto.getAddress05());
                locationWebMapper.insert(ins);

                return sysUser.getUserId();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            throw new IllegalArgumentException("Số điện thoại đang bị trùng - Liên hệ đội IT để được hỗ trợ");
        }
    }

    @Override
    public Integer softDeleteById(Integer id) {
        if (Objects.isNull(sysUserManagermentMapper.isExistUser(id))) {
            throw new IllegalArgumentException("User with id " + id + " does not exist");
        }
        return sysUserManagermentMapper.softDeleteById(id);
    }

    @Override
    public Integer update(SysUserRequestDto sysUserRequestDto) {
        try {
            if (ObjectUtils.isEmpty(sysUserRequestDto.getUserId())) {
                throw new IllegalArgumentException("User ID must not be null for update");
            }

            // Nếu có nhập sđt mới thì check trùng
            if (!ObjectUtils.isEmpty(sysUserRequestDto.getPhoneNumber())) {
                List<Integer> listUserId = sysUserManagermentMapper.findUserIdsByPhoneNumberExcludingId(
                        sysUserRequestDto.getPhoneNumber(),
                        String.valueOf(sysUserRequestDto.getUserId())
                );
                if (!listUserId.isEmpty()) {
                    throw new IllegalArgumentException("Số điện thoại đang bị trùng - Liên hệ đội IT để được hỗ trợ");
                }
            }

            // Build entity update
            SysUser sysUser = sysUserManagermentConverter.toEntity(sysUserRequestDto);
            if (!ObjectUtils.isEmpty(sysUser.getPassword())) {
                sysUser.setPassword(passwordEncoder.encode(sysUser.getPassword()));
            }

            sysUserManagermentMapper.update(sysUser);

            // Update location
            Location upd = uLocationEntityFromSysUserDto(sysUserRequestDto);
            upd.setUserId(sysUser.getUserId());
            locationWebMapper.update(upd);

            return 1;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Integer countAll() {
        return sysUserManagermentMapper.countAll();
    }

    @Override
    @Transactional
    public String upsert(SysUserRequestDto sysUserRequestDto) {
        // check exist phone number
        List<Integer> listUserId = sysUserManagermentMapper.getListUserIdByPhoneNumber(sysUserRequestDto.getPhoneNumber());
        if (listUserId == null || listUserId.isEmpty()) {
            // Insert User
            this.insert(sysUserRequestDto);
            // Insert Location
            Location ins = uLocationEntityFromSysUserDto(sysUserRequestDto);
            ins.setUserId(sysUserManagermentMapper.getListUserIdByPhoneNumber(sysUserRequestDto.getPhoneNumber()).getFirst());
            ins.setFullAddress(sysUserRequestDto.getAddress01() + ", " + sysUserRequestDto.getAddress02()
                    + ", " + sysUserRequestDto.getAddress03() + ", " + sysUserRequestDto.getAddress04() + ", " + sysUserRequestDto.getAddress05());
            locationWebMapper.insert(ins);
            return "Insert Success";
        } else if (listUserId.size() == 1) {
            // Update User
            sysUserRequestDto.setUserId(listUserId.getFirst());
            this.update(sysUserRequestDto);
            Location upd = uLocationEntityFromSysUserDto(sysUserRequestDto);
            upd.setUserId(listUserId.getFirst());
            locationWebMapper.update(upd);
            return "Update Success";
        } else {
            throw new IllegalArgumentException("Số Điện Thoại Đang Bị Trùng - Liên hệ đội IT để được hỗ trợ");
        }
    }

    Location uLocationEntityFromSysUserDto(SysUserRequestDto sysUserRequestDto) {
        return Location.builder()
                .latitude(sysUserRequestDto.getLatitude())
                .longitude(sysUserRequestDto.getLongitude())
                .address01(sysUserRequestDto.getAddress01())
                .address02(sysUserRequestDto.getAddress02())
                .address03(sysUserRequestDto.getAddress03())
                .address04(sysUserRequestDto.getAddress04())
                .address05(sysUserRequestDto.getAddress05())
                .fullAddress(sysUserRequestDto.getAddress01() + ", " + sysUserRequestDto.getAddress02()
                        + ", " + sysUserRequestDto.getAddress03() + ", " + sysUserRequestDto.getAddress04() + ", " + sysUserRequestDto.getAddress05())
                .build();
    }
}
