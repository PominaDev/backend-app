package com.pomina.webapp.pricing_policy_management.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ChinhSachParentRequestDto {
    @JsonProperty("uChinhSachParentId")
    private Integer uChinhSachParentId;
    private String uChinhSachParentType;
    @JsonProperty("uChinhSachParentCode")
    private String uChinhSachParentCode;
    @JsonProperty("uChinhSachParentName")
    private String uChinhSachParentName;
    private Integer userId;
    private String masterGroupUserCode;
    @JsonProperty("uDayBegin")
    private LocalDateTime uDayBegin;
    @JsonProperty("uDayEnd")
    private LocalDateTime uDayEnd;
    @JsonProperty("uStatus")
    private String uStatus;
    @JsonProperty("uDescription")
    private String uDescription;
    private String status;
    private LocalDateTime dateUpload;
    private String deptUpload;
    private String userUpload;
    private String urlGoogledriv;
    private String urlYoutube;
}
