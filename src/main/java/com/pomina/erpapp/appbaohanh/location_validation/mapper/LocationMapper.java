package com.pomina.erpapp.appbaohanh.location_validation.mapper;

import com.pomina.erpapp.appbaohanh.location_validation.entity.Location;
import com.pomina.erpapp.appbaohanh.common.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface LocationMapper extends BaseMapper<Location> {
    Location findByUserId(@Param("userId") Integer userId);
}
