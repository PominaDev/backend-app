package com.pomina.common.exception;

import lombok.Getter;
/**
 * Mã lỗi hệ thống chia theo nhóm:
 * - 5000: Lỗi hệ thống / bảo mật / xác thực
 * - 4000: Lỗi từ dịch vụ bên ngoài (Google, Vonage,...)
 * - 3000: Lỗi định dạng / dữ liệu không hợp lệ
 * - 2000: Không tìm thấy / đã tồn tại
 */
@Getter
public enum ErrorCode {

    // ===== 5000 - Hệ thống, bảo mật, xác thực =====
    INTERNAL_ERROR("5000", "Lỗi hệ thống"),
    UNAUTHORIZED("5000", "Không có quyền truy cập"),
    BAD_CREDENTIALS("5000", "Đã sai tài khoản và mật khẩu"),
    ACCOUNT_DISABLED("5000", "Tài khoản đã bị vô hiệu hóa"),
    TOKEN_EXPIRED("5000", "Token đã hết hạn"),
    INVALID_REFRESH_TOKEN("5000", "Lỗi refresh token"),
    INVALID_TOKEN("5000", "Token không hợp lệ"),
    VERIFICATION_FAILED("5000", "Xác minh thất bại"),
    OTP_EXPIRED("5000", "Mã OTP đã hết hạn"),
    OTP_FAILED("5000", "Mã OTP không đúng"),

    // ===== 4000 - Dịch vụ bên ngoài =====
    LOCATION_NOT_FOUND("4000", "Không tìm thấy vị trí"),
    GOOGLE_API_ERROR("4001", "Lỗi từ Google Geocode API"),
    VONAGE_ERROR("4002", "Lỗi từ Vonage SMS API"),
    ZALO_ZNS_ERROR("4003", "Lỗi từ Zalo ZNS"),
    INVALID_REQUEST("4000", "Yêu cầu không hợp lệ"),

    // ===== 3000 - Dữ liệu không hợp lệ =====
    INVALID_PHONE_NUMBER("3000", "Sai định dạng số điện thoại"),
    INVALID_OTP("3001", "Mã OTP không hợp lệ"),
    INVALID_EMAIL_FORMAT("3002", "Định dạng email không hợp lệ"),
    INVALID_LAT_LONG("3003", "Tọa độ không hợp lệ"),
    INVALID_OWN_PRODUCT("3004", "Sản phẩm này không thuộc quyền sở hữu của bạn!"),
    INVALID_DATE_RANGE("30005", "Ngày bắt đầu phải nhỏ hơn ngày kết thúc"),

    // ===== 3100 - Excel
    IMPORT_EXCEL_FAILED("3100", "Import Excel failed"),
    FILE_IS_EMPTY("3101", "File is empty"),
    FILE_PROCESSING_ERROR("3102", "Failed to read file"),
    FILE_TOO_LARGE("3103", "File too LARGE"),
    INVALID_FILE_TYPE("3104", "INVALID_FILE_TYPE"),
    EXPORT_EXCEL_FAILED("3105", "Export Excel failed"),
    EXCEL_FILE_NOT_FOUND("3106", "Excel file not found"),

    // ===== 2000 - Không tìm thấy / Đã tồn tại =====
    USER_NOT_FOUND("2000", "Người dùng không tồn tại"),
    PRODUCT_NOT_FOUND("2000", "Không tìm thấy sản phẩm"),
    WARRANTY_NOT_FOUND("2000", "Không tìm thấy thông tin bảo hành"),
    USER_LOCATION_NOT_FOUND("2000", "Không tìm thấy vị trí người dùng"),
    POLICY_NOT_FOUND("2000", "Chính sách không tồn tại"),


    USER_EXISTED("2003", "Người dùng đã tồn tại"),
    PRODUCT_EXISTED("2000", "Sản phẩm đã tồn tại"),
    PRODUCT_ALREADY_ACTIVATED("2004", "Sản phẩm đã được kích hoạt"),
    OUT_OF_LOCATION("2005", "Không ở trong khu vực đã đăng ký"),

    // ===== 1000 - Khác (tuỳ chọn mở rộng) =====
    ACTION_NOT_ALLOWED("1000", "Hành động không được phép"),
    FEATURE_NOT_AVAILABLE("1001", "Tính năng chưa khả dụng"),
    BAD_SQL("1001", "Sai câu lệnh sql"),
    FAILED_ENCODED_PASSWORD("1000", "Lỗi mã hóa mật khẩu")

    ;

    private final String code;
    private final String message;

    ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
