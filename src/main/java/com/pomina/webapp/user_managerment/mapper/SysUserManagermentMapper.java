package com.pomina.webapp.user_managerment.mapper;

import com.pomina.webapp.user_managerment.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysUserManagermentMapper {
    SysUser findById(Integer id);

    List<SysUser> findAll();

    List<SysUser> findAllPaged(@Param("filter") List<String> filter, @Param("roleIds") List<Integer> roIds, Integer limit, Integer offset);

    Integer countAllWithFilter(@Param("filter") List<String> filter);

    Byte isExistUser(Integer id);

    Integer insert(SysUser sysUser);

    Integer update(SysUser sysUser);

    Integer deleteById(Integer id);          // Xóa cứng

    Integer softDeleteById(Integer id);      // Xóa mềm

    Integer countAll();

    List<Integer> getListUserIdByPhoneNumber(String phoneNumber);

    List<Integer> findUserIdsByPhoneNumberExcludingId(String phoneNumber, String userId);

    List<SysUser> findAllFilter(@Param("filter") List<String> filter, @Param("roleIds") List<Integer> roleIds);
}