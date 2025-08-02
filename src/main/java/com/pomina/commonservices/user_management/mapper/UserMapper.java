package com.pomina.commonservices.user_management.mapper;

import com.pomina.app.profile.dto.request.UserProfileRequestDto;
import com.pomina.common.mapper.BaseMapper;
import com.pomina.commonservices.user_management.dto.custom_mapper.UserProfile;
import com.pomina.commonservices.user_management.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper extends BaseMapper<SysUser> {

    UserProfile getUserProfileByUserId(@Param("userId") Integer userId);

    int updateProfile(@Param("userId") Integer userId, @Param("userProfile") UserProfileRequestDto userProfile);
}
