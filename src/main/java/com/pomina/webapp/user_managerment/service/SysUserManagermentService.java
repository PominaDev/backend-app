package com.pomina.webapp.user_managerment.service;


import com.pomina.common.model.PageResponse;
import com.pomina.webapp.user_managerment.dto.request.SysUserRequestDto;
import com.pomina.webapp.user_managerment.dto.respone.SysUserResponeDto;
import com.pomina.webapp.user_managerment.entity.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysUserManagermentService {

    SysUser findById(Integer id);

    List<SysUserResponeDto> findAll();

    PageResponse<SysUserResponeDto> findAllPaged(List<String> filter, List<String> roleNames, Integer page, Integer size);

    Boolean existsById(Integer id);

    Integer insert(SysUserRequestDto sysUserRequestDto);

    Integer update(SysUserRequestDto sysUserRequestDto);

    Integer deleteById(Integer id);          // Xóa cứng

    Integer softDeleteById(Integer id);      // Xóa mềm

    Integer countAll();

    String upsert(SysUserRequestDto sysUserRequestDto);

    List<SysUser> findAllFilter(List<String> filter, List<Integer> roleIds);
}
