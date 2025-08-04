package com.pomina.webapp.master_location_managerment.mapper;

import com.pomina.webapp.master_location_managerment.entity.MasterLocation;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MasterLocationMapper {
    List<MasterLocation> findAll();
}
