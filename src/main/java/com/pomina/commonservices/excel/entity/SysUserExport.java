package com.pomina.commonservices.excel.entity;

import com.pomina.webapp.user_managerment.entity.SysUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SysUserExport extends SysUser {

    private String stt;
}
