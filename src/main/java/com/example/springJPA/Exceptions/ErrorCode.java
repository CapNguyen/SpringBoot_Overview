package com.example.springJPA.Exceptions;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(1000, "Uncategorized error", HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_KEY(1001, "Invalid exception key", HttpStatus.BAD_REQUEST),
    USER_EXISTED(999, "User existed", HttpStatus.BAD_REQUEST),
    USERNAME_INVALID(998, "Username must be at least 3 characters", HttpStatus.BAD_REQUEST),
    PASSWORD_INVALID(997, "Password must be at least 8 characters", HttpStatus.BAD_REQUEST),
    FIELD_CANNOT_EMPTY(996, "There is a non-blank field", HttpStatus.BAD_REQUEST),
    USER_NOT_EXISTED(995, "User not existed", HttpStatus.NOT_FOUND),
    UNAUTHENTICATED(994, "Unauthenticated", HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED(994, "You don't have permission", HttpStatus.FORBIDDEN),
    ;


    int code;
    String message;
    HttpStatusCode statusCode;

    ErrorCode(int code, String message, HttpStatusCode statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }
}
