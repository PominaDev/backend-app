package com.pomina.servicecommon.location.mapper;

import com.pomina.common.mapper.BaseMapper;
import com.pomina.servicecommon.location.entity.Location;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface LocationMapper extends BaseMapper<Location> {
    Location findByUserId(@Param("userId") Integer userId);
}
