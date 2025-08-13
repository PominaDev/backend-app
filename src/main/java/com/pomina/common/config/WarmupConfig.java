package com.pomina.common.config;

import com.pomina.common.logging.LogService;
import com.pomina.security.sysmodel.RefreshTokenInfo;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Collection;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
public class WarmupConfig {

    @Bean
    public ApplicationRunner warmupMappers(SqlSessionFactory factory, LogService logService) {
        return args -> {
            // Giới hạn số thread để tránh nghẽn DB
            int threads = Math.min(Runtime.getRuntime().availableProcessors(), 8);
            ExecutorService executor = Executors.newFixedThreadPool(threads);

            // Lấy tất cả mapper
            Collection<Class<?>> mappers = factory.getConfiguration().getMapperRegistry().getMappers();

            long start = System.currentTimeMillis();
            for (Class<?> mapper : mappers) {
                executor.submit(() -> {
                    try (SqlSession session = factory.openSession()) {
                        session.getMapper(mapper); // Trigger load
                    } catch (Exception e) {
                        System.err.println("Warm-up failed for mapper: " + mapper.getName());
                        e.printStackTrace();
                    }
                });
            }

            executor.shutdown();
            while (!executor.isTerminated()) {
                Thread.sleep(50); // Chờ tất cả task xong
            }
            long end = System.currentTimeMillis();
            logService.logConfig("MyBatis Warmup", "Loaded " + mappers.size() + " mappers in " + (end - start) + " ms");
        };
    }

    @Bean
    public ApplicationRunner warmupRedis(
            RedisTemplate<String, RefreshTokenInfo> refreshTokenTemplate,
            RedisTemplate<String, String> stringTemplate) {
        return args -> {
            // Warm-up template chứa RefreshTokenInfo
            warmupRedisTemplate(refreshTokenTemplate, new RefreshTokenInfo());

            // Warm-up template String -> String
            warmupRedisTemplate(stringTemplate, "warmup");
        };
    }

    private <T> void warmupRedisTemplate(RedisTemplate<String, T> template, T sampleValue) {
        String key = "warmup:" + sampleValue.getClass().getSimpleName();
        try {
            template.opsForValue().set(key, sampleValue);
            template.opsForValue().get(key);
            template.delete(key); // dọn sau warmup
        } catch (Exception e) {
            System.err.println("Redis warmup failed: " + e.getMessage());
        }
    }
}
