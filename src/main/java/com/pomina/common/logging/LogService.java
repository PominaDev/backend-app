package com.pomina.common.logging;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class LogService {

    @Async
    public void logBusiness(String message, String s) {
        log.info("[BUSINESS] {}", message);
    }

    @Async
    public void logAudit(String user, String action) {
        log.info("[AUDIT] User {} performed {}", user, action);
    }

    @Async
    public void logConfig(String configType, String message) {
        log.info("[ {} ] {}", configType, message);
    }
}
