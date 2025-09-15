package com.pomina.webapp.pricing_policy_management.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ChinhSachParentResponseDto {

    private Integer uChinhSachParentId;

    private String uChinhSachParentType;

    private String uChinhSachParentCode;

    private String uChinhSachParentName;

    private Integer userId;

    private String masterGroupUserCode;

    private LocalDateTime uDayBegin;

    private LocalDateTime uDayEnd;

    private String uStatus;

    @JsonProperty("description")
    private String uDescription;

    private LocalDateTime dateUpload;

    private String deptUpload;

    private String userUpload;

    private String urlGoogledriv;

    private String urlYoutube;
}
