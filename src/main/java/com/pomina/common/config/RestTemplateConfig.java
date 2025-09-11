package com.pomina.common.config;

import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManagerBuilder;
import org.apache.hc.core5.util.TimeValue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.TimeUnit;

@Configuration
public class RestTemplateConfig {

//    /**
//     * Quản lý Connection Pool
//     */
//    @Bean
//    protected PoolingHttpClientConnectionManager connectionManager() {
//        return PoolingHttpClientConnectionManagerBuilder.create()
//                .setMaxConnTotal(100) // Tổng số kết nối tối đa trong pool
//                .setMaxConnPerRoute(20) // Số kết nối tối đa cho mỗi route
//                .setConnectionTimeToLive(TimeValue.ofMinutes(5)) // TTL
//                .build();
//    }
//
//    /**
//     * Cấu hình timeout request
//     */
//    @Bean
//    protected RequestConfig requestConfig() {
//        return RequestConfig.custom()
//                .setConnectionRequestTimeout(4, TimeUnit.SECONDS) // Timeout lấy kết nối từ pool
//                .setConnectTimeout(4, TimeUnit.SECONDS) // Timeout thiết lập kết nối
//                .setResponseTimeout(10, TimeUnit.SECONDS) // Timeout đọc response
//                .build();
//    }
//
//    /**
//     * Tạo Rest Template
//     */
//    @Bean
//    public RestTemplate restTemplate(PoolingHttpClientConnectionManager connectionManager, RequestConfig requestConfig) {
//
//        HttpClient httpClient = HttpClients.custom()
//                .setConnectionManager(connectionManager) // Sử dụng connection pool đã định nghĩa: Tăng hiệu suất bằng cách tái sử dụng kết nối.
//                .setDefaultRequestConfig(requestConfig) // Áp dụng cấu hình timeout cho mọi request: Đảm bảo tính nhất quán.
//                .setConnectionManagerShared(true) // Cho phép chia sẻ connection manager giữa nhiều HttpClient: Tiết kiệm tài nguyên, phù hợp khi có nhiều RestTemplate.
//                .evictExpiredConnections() // Tự động xóa kết nối hết hạn: Ngăn rò rỉ tài nguyên, tốt cho ứng dụng chạy lâu dài.
//                .evictIdleConnections(TimeValue.ofSeconds(30)) // Xóa kết nối không hoạt động sau 30s: Giảm lãng phí tài nguyên, giá trị 30s là hợp lý.
//                .build();
//
//        return new RestTemplate(
//                new HttpComponentsClientHttpRequestFactory(httpClient)
//        );
//    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
