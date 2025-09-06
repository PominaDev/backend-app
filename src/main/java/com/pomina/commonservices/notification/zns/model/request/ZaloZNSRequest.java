package com.pomina.commonservices.notification.zns.model.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@Builder
public class ZaloZNSRequest {

    private String mode;
    /*
    Tham số cho biết thông báo sẽ được gửi ở chế độ development. Giá trị nhận vào:
    development
     */

    private String phone;
    /*
    SĐT của client.
    (viết theo định dạng chuẩn hóa của Việt Nam là 84987654321 hoặc +84987654321)
     */

    private String templateId;
    /*
    ID của template muốn sử dụng.
    template_id sẽ được phía Zalo cung cấp riêng cho từng đối tác.
     */

    private Map<String, Object> templateData;
    /*
    Các thuộc tính của template mà đối tác đã đăng ký với Zalo.
    Lưu ý: Cấu trúc template_data được quy định riêng ứng với từng template.
     */

    private String trackingId;
    /*
    Mã số đánh dấu lần gọi API của đối tác, do đối tác định nghĩa. Đối tác có thể dùng tracking_id để đối soát mà không phụ thuộc vào message_id của Zalo cung cấp.
    Lưu ý: Tham số có độ dài tối đa 48 ký tự và không chứa kí tự đặc biệt.
    */
}
