package com.pomina.webapp.pricing_policy_management.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ChinhSachParentUpdateDto {

    private Integer chinhSachParentId;

    private String chinhSachParentType;

    private String chinhSachParentCode;

    private String chinhSachParentName;

    private Integer userId;

    private String masterGroupUserCode;

    private LocalDateTime dayBegin;

    private LocalDateTime dayEnd;

    private String statusU;

    private String description;

    private LocalDateTime dateUpload;

    private String deptUpload;

    private String userUpload;

    private String urlGoogledriv;

    private String urlYoutube;
}
