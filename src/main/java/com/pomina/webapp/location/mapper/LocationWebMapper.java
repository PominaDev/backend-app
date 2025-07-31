package com.pomina.webapp.location.mapper;

import com.pomina.common.mapper.BaseMapper;
import com.pomina.webapp.location.entity.Location;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LocationWebMapper extends BaseMapper<Location> {
}
