package com.pomina.webapp.grant_approval.mapper;

import com.pomina.common.mapper.BaseMapper;
import com.pomina.webapp.grant_approval.entity.MasterGroupUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MasterGroupUserMapper extends BaseMapper<MasterGroupUser> {
    List<MasterGroupUser> getByMasterGroupUserCode(@Param("groupCode") String groupCode);

    boolean isExistByUserIdAndGroupCode(@Param("groupCode") String groupCode,@Param("userId")  Integer userId);

}
