package com.pomina.commonservices.notification.zns.model.entity;

import com.pomina.common.model.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ZaloOaToken extends BaseEntity {

    private Long id;

    private String accessToken;

    private String refreshToken;

    private String expiresIn;
}
