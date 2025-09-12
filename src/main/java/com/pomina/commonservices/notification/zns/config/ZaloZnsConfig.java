package com.pomina.commonservices.notification.zns.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "zalo.zns")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ZaloZnsConfig {

    @Value("${zalo.zns.url.send}")
    private String urlZaloZNS;

    @Value("${zalo.zns.url.refresh-token}")
    private String urlRefreshToken;

    @Value("${zalo.zns.app-id}")
    private String appIdZaloZNS;

    @Value("${zalo.zns.secret-key}")
    private String secretKeyZaloZNS;
}
