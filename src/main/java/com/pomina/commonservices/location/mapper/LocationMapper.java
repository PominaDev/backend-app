package com.pomina.commonservices.location.mapper;

import com.pomina.common.mapper.BaseMapper;
import com.pomina.commonservices.location.entity.Location;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface LocationMapper extends BaseMapper<Location> {
    Location findByUserId(@Param("userId") Integer userId);
}
