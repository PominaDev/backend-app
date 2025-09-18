package com.pomina.commonservices.marketing_pr_chinh_sach.entity;

import com.pomina.common.model.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MarketingPrChinhSach extends BaseEntity {

    private Integer uMarketingPrChinhSachId;

    private String uChinhSachParentCode;

    private String uChinhSachParentName;

    private LocalDateTime uDayBegin;

    private LocalDateTime uDayEnd;

    private String urlGoogleDrive;

    private String urlYoutube;

    private String masterLocationCode;

    private String userUpload;

    private String uStatus;
}
