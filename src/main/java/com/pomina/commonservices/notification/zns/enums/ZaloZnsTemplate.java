package com.pomina.commonservices.notification.zns.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
@AllArgsConstructor
public enum ZaloZnsTemplate {
    ZALO_OTP("484909", Map.of("otp", "{otp}")),
    ;

    private final String templateId;

    private final Map<String, Object> templateData;

    public Map<String, Object> buildTemplateData(Map<String, Object> templateData) {
        Map<String, Object> finalData = new HashMap<>(templateData);
        finalData.putAll(templateData);
        return finalData;
    }
}
