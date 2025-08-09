package com.pomina.webapp.policy_management.mapper;

import com.pomina.common.mapper.BaseMapper;
import com.pomina.webapp.policy_management.entity.UPolicyParent;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UPolicyParentMapper extends BaseMapper<UPolicyParent> {
    List<UPolicyParent> findAll();
    List<UPolicyParent> findByPolicyParentCode(@Param("code")String code);
    Integer insertList(@Param("list") List<UPolicyParent> uPolicyParents);
    Integer updateList(@Param("list")List<UPolicyParent> uPolicyParents);
    Integer softDeleteList(@Param("list")List<Integer> listId);

}
