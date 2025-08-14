package com.pomina.webapp.pricing_policy_management.mapper;

import com.pomina.common.mapper.BaseMapper;
import com.pomina.common.model.PageRequest;
import com.pomina.webapp.pricing_policy_management.entity.ChinhSachGiaBanChild;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ChinhSachGiaBanChildMapper extends BaseMapper<ChinhSachGiaBanChild> {
    int batchInsert(List<ChinhSachGiaBanChild> chinhSachGiaBanChildList);

    List<ChinhSachGiaBanChild> findByUChinhSachParentId(@Param("offset") int offset,
                                                        @Param("limit") int limit,
                                                        @Param("paging") PageRequest pageRequest,
                                                        @Param("uChinhSachParentId") int uChinhSachParentId);

    int countFindByUChinhSachParentId(@Param("uChinhSachParentId") int uChinhSachParentId);
}
