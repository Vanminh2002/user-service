package com.example.userservice.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    INTERNAL_SERVER_ERROR(500, "Lỗi hệ thống", HttpStatus.INTERNAL_SERVER_ERROR),
    UNCATEGORIZED_EXCEPTION(999, "Lỗi chưa xác định", HttpStatus.INTERNAL_SERVER_ERROR),
    USER_NOT_FOUND(1000, "Người dùng không tồn tại", HttpStatus.BAD_REQUEST),
    USER_EXISTS(1001, "Người dùng đã tồn tại", HttpStatus.BAD_REQUEST),
    USERNAME_INVALID(1002, "Username phải có ít nhất 3 kí tự và không được chứa kí tự đặc biệt", HttpStatus.BAD_REQUEST),
    PASSWORD_INVALID(1003, "Mật khẩu phải có ít nhất 3 kí tự, ít nhất 1 chữ hoa, 1 chữ thường, 1 số và 1 ký tự đặc biệt", HttpStatus.BAD_REQUEST),
    VALIDATION_FAILED(1004, "Dữ liệu nhập vào không hợp lệ", HttpStatus.BAD_REQUEST),
    EMAIL_EXISTS(1005, "Gmail đã tồn tại", HttpStatus.BAD_REQUEST),
    PHONE_EXISTS(1006, "Số điện thoại đã tồn tại", HttpStatus.BAD_REQUEST),
    UNAUTHENTICATED(1007, "Chưa được xác thực", HttpStatus.BAD_REQUEST),
    PERMISSION_EXISTS(1008, "Quyền này đã tồn tại", HttpStatus.BAD_REQUEST),
    ROLE_EXISTS(1009, "Quyền này đã tồn tại", HttpStatus.BAD_REQUEST),
    NOT_FOUND(1010, "Không có dữ liệu để hiển thị", HttpStatus.BAD_REQUEST),
    AUTHORIZED(1011, "Bạn không có quyền để truy cập dữ liệu", HttpStatus.FORBIDDEN),
    AUTHENTICATED(1012,"Bạn cần đăng nhập",HttpStatus.UNAUTHORIZED)
    ;


    ErrorCode(int code, String message, HttpStatus status) {
        this.code = code;
        this.message = message;
        this.status = status;
    }

    private int code;
    private String message;
    private HttpStatus status;

}
