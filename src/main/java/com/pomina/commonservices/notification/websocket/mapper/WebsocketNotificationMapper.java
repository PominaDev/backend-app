package com.pomina.commonservices.notification.websocket.mapper;

import com.pomina.common.mapper.BaseMapper;
import com.pomina.commonservices.notification.websocket.entity.Notification;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WebsocketNotificationMapper extends BaseMapper<Notification> {
}
