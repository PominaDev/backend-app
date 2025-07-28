package com.pomina.appbaohanh.location.mapper;

import com.pomina.appbaohanh.common.mapper.BaseMapper;
import com.pomina.appbaohanh.location.entity.Location;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface LocationMapper extends BaseMapper<Location> {
    Location findByUserId(@Param("userId") Integer userId);
}
