package com.pomina.webapp.grant_approval.mapper;

import com.pomina.common.mapper.BaseMapper;
import com.pomina.webapp.grant_approval.entity.MasterGroupUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MasterGroupUserMapper extends BaseMapper<MasterGroupUser> {
    List<MasterGroupUser> findAll();
    List<MasterGroupUser> findByMasterGroupUserCode(@Param("groupCode") String groupCode);
    Integer insertList(List<MasterGroupUser> masterGroupUserList);
    Integer updateList(List<MasterGroupUser> masterGroupUserList);
    Integer softDeleteList(@Param("list")List<Integer> idList);
    boolean isExistByUserIdAndGroupCode(@Param("groupCode") String groupCode,@Param("userId")  Integer userId);


}
