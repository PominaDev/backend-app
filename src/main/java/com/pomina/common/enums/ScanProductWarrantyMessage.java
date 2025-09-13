package com.pomina.common.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ScanProductWarrantyMessage {

    INVALID("Vị trí quét của bạn không trùng với vị trí đăng ký xưởng cán, vì thế bạn sẽ không được nhận chiết khấu cuộn hàng này, liên hệ hotline 0906687917 để được hỗ trợ");

    private final String message;
}
