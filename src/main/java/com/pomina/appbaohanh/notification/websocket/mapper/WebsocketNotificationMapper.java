package com.pomina.appbaohanh.notification.websocket.mapper;

import com.pomina.appbaohanh.common.mapper.BaseMapper;
import com.pomina.appbaohanh.notification.websocket.entity.Notification;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WebsocketNotificationMapper extends BaseMapper<Notification> {
}
