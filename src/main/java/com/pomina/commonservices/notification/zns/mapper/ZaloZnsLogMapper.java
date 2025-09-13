package com.pomina.commonservices.notification.zns.mapper;

import com.pomina.commonservices.notification.zns.model.entity.ZaloZnsLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ZaloZnsLogMapper {

    @Insert("INSERT INTO sys_zalo_zns_log (access_token, phone, template_id, template_data, tracking_id, error, message, msg_id, sent_time, dailyQuota, remainingQuota) " +
            "VALUES (#{accessToken}, #{phone}, #{templateId}, #{templateData}, #{trackingId}, #{error}, #{message}, #{msgId}, #{sentTime}, #{dailyQuota}, #{remainingQuota})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(ZaloZnsLog log);

    @Select("SELECT * FROM sys_zalo_zns_log WHERE tracking_id = #{trackingId} LIMIT 1")
    ZaloZnsLog findByTrackingId(@Param("trackingId") String trackingId);

    @Select("SELECT * FROM sys_zalo_zns_log WHERE phone = #{phone} ORDER BY sent_time DESC LIMIT 10")
    List<ZaloZnsLog> findByPhone(@Param("phone") String phone);
}
