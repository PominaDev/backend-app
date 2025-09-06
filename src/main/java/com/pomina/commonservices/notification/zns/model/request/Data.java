package com.pomina.commonservices.notification.zns.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Data {

    private String sentTime; // Thời gian gửi thông báo ZNS (định dạng timestamp).

    private String msgId; // ID của thông báo ZNS.
}
