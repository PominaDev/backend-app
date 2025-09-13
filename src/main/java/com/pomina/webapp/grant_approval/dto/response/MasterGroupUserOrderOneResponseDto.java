package com.pomina.webapp.grant_approval.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MasterGroupUserOrderOneResponseDto {
    private String masterGroupUserCode;
    private String masterGroupUserName;
    private Byte masterGroupUserOrder;
    private String userId;
}
