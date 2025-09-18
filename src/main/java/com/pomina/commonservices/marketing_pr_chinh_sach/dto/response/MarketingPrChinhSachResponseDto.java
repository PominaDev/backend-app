package com.pomina.commonservices.marketing_pr_chinh_sach.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class MarketingPrChinhSachResponseDto {

    private Integer uMarketingPrChinhSachId;

    private String uChinhSachParentCode;

    private String uChinhSachParentName;

    private LocalDateTime uDayBegin;

    private LocalDateTime uDayEnd;

    private String urlGoogleDrive;

    private String urlYoutube;

    private String masterLocationCode;

    private String status;

    private String userUpload;

    private String uStatus;
}
