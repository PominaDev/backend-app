package com.pomina.commonservices.entity;

import com.pomina.webapp.user_managerment.entity.SysUser;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SysUserExport extends SysUser {
    private String stt;
}
