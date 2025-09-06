package com.pomina.commonservices.notification.zns.service;

import com.pomina.commonservices.notification.zns.model.request.ZaloZNSRequest;
import com.pomina.commonservices.notification.zns.model.response.OaAccessTokenResponse;
import com.pomina.commonservices.notification.zns.model.response.ZaloZNSResponse;

public interface ZNSService {

    ZaloZNSResponse sendZaloZNS(ZaloZNSRequest request);

    OaAccessTokenResponse renewAccessToken();
}
