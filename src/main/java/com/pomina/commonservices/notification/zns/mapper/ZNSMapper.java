package com.pomina.commonservices.notification.zns.mapper;

import com.pomina.commonservices.notification.zns.model.entity.ZNSEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ZNSMapper {
    @Insert("INSERT INTO zns_sms_tracking (`from`, to_phone, client_req_id, status, tracking_id, error_code, description, request_time, response_time, created_at, updated_at) " + "VALUES (#{from}, #{toPhone}, #{clientReqId}, #{status}, #{trackingId}, #{errorCode}, #{description}, #{requestTime}, #{responseTime}, NOW(), NOW())")
    void insert(ZNSEntity znsEntity);

    @Select("SELECT * FROM zns_sms_tracking WHERE tracking_id = #{trackingId}")
    @Results({@Result(column = "from", property = "from"), @Result(column = "to_phone", property = "toPhone"), @Result(column = "client_req_id", property = "clientReqId"), @Result(column = "status", property = "status"), @Result(column = "tracking_id", property = "trackingId"), @Result(column = "error_code", property = "errorCode"), @Result(column = "description", property = "description"), @Result(column = "request_time", property = "requestTime"), @Result(column = "response_time", property = "responseTime"), @Result(column = "created_at", property = "createdDate"), @Result(column = "updated_at", property = "updatedDate")})
    ZNSEntity getByTrackingId(@Param("trackingId") String trackingId);

    @Select("SELECT * FROM zns_sms_tracking")
    List<ZNSEntity> getAll();
}
