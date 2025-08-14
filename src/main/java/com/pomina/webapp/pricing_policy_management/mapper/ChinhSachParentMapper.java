package com.pomina.webapp.pricing_policy_management.mapper;

import com.pomina.common.mapper.BaseMapper;
import com.pomina.webapp.pricing_policy_management.entity.ChinhSachParent;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ChinhSachParentMapper extends BaseMapper<ChinhSachParent> {
    int batchInsert(List<ChinhSachParent> chinhSachParentList);
}
