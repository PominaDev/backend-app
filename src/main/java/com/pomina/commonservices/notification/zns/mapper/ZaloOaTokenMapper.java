package com.pomina.commonservices.notification.zns.mapper;

import com.pomina.commonservices.notification.zns.model.entity.ZaloOaToken;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ZaloOaTokenMapper {

    @Select("SELECT access_token, refresh_token FROM sys_zalo_oa_token WHERE is_deleted = 0 ORDER BY created_at DESC LIMIT 1")
    ZaloOaToken findLatestToken();

    @Insert("INSERT INTO sys_zalo_oa_token (access_token, refresh_token, expires_in, status, noted, created_by, updated_by) " +
            "VALUES (#{accessToken}, #{refreshToken}, #{expiresIn}, #{status}, #{noted}, #{createdBy}, #{updatedBy})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(ZaloOaToken token);
}
