package com.pomina.commonservices.marketing_pr_chinh_sach.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class MarketingPrChinhSachUpdateDto {

    @JsonProperty("uMarketingPrChinhSachId")
    private Integer uMarketingPrChinhSachId;

    @JsonProperty("uChinhSachParentCode")
    private String uChinhSachParentCode;

    @JsonProperty("uChinhSachParentName")
    private String uChinhSachParentName;

    @JsonProperty("uDayBegin")
    private LocalDateTime uDayBegin;

    @JsonProperty("uDayEnd")
    private LocalDateTime uDayEnd;

    private String urlGoogleDrive;

    private String urlYoutube;

    private String masterLocationCode;

    @JsonProperty("uStatus")
    private String uStatus;
}
