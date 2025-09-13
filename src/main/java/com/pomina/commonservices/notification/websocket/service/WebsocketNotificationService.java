package com.pomina.commonservices.notification.websocket.service;

import com.pomina.commonservices.notification.websocket.dto.NotificationRequestDto;
import com.pomina.commonservices.notification.websocket.entity.Notification;
import com.pomina.commonservices.notification.websocket.mapper.WebsocketNotificationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WebsocketNotificationService {

    private final WebsocketNotificationMapper websocketNotificationMapper;

    private final SimpMessagingTemplate messagingTemplate;

    public void sendNotification(NotificationRequestDto requestDto) {

        Notification notification = new Notification();
        // Lưu vào database
        websocketNotificationMapper.insert(notification);

        // Gửi real-time qua WebSocket
        messagingTemplate.convertAndSend("/topic/notifications", notification);
    }
}
