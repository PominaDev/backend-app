package com.pomina.webapp.pricing_policy_management.mapper;

import com.pomina.common.mapper.BaseMapper;
import com.pomina.webapp.pricing_policy_management.entity.ChinhSachGiaBanChild;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ChinhSachGiaBanChildMapper extends BaseMapper<ChinhSachGiaBanChild> {
    int batchInsert(List<ChinhSachGiaBanChild> chinhSachGiaBanChildList);
}
