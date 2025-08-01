package com.pomina.webapp.location.mapper;

import com.pomina.common.mapper.BaseMapper;
import com.pomina.webapp.location.entity.Location;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.transaction.annotation.Transactional;

@Mapper
@Transactional
public interface LocationWebMapper extends BaseMapper<Location> {
}
