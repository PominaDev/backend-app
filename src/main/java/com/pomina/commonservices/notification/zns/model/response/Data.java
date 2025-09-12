package com.pomina.commonservices.notification.zns.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Data {

    @JsonProperty("msg_id")
    private String msgId; // ID của thông báo ZNS.

    @JsonProperty("sent_time")
    private String sentTime; // Thời gian gửi thông báo ZNS (định dạng timestamp).

    @JsonProperty("sending_mode")
    private String sendingMode;
    /*
    Bao gồm giá trị:
    1: tin ZNS được gửi theo cơ chế thông thường
    3: tin ZNS được gửi theo cơ chế vượt hạn mức
    */

    private Quota quota;
}