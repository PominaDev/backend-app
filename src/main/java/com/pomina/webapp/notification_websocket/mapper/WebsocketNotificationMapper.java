package com.pomina.webapp.notification_websocket.mapper;

import com.pomina.common.mapper.BaseMapper;
import com.pomina.webapp.notification_websocket.entity.Notification;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WebsocketNotificationMapper extends BaseMapper<Notification> {
}
