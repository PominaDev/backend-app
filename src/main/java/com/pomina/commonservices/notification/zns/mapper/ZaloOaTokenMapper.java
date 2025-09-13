package com.pomina.commonservices.notification.zns.mapper;

import com.pomina.commonservices.notification.zns.model.entity.ZaloOaToken;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ZaloOaTokenMapper {

    ZaloOaToken findLatestToken();

    int insert(ZaloOaToken token);
}
