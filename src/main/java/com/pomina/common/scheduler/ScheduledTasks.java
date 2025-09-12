package com.pomina.common.scheduler;

import com.pomina.commonservices.notification.zns.service.ZNSService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ScheduledTasks {

    private final ZNSService znsService;

    @Scheduled(cron = "0 0 02 * * ?")
    public void refreshTokenZaloZns() {
        znsService.renewAccessToken();
    }
}
