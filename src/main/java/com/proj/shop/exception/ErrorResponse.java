package com.proj.shop.exception;


import com.proj.shop.constant.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {
    private String message;

    private String status;
    private int code;
    private ZonedDateTime responseTime;

    public ErrorResponse(String message, ErrorCode errorCode){
        this.message = message;
        this.code = errorCode.getCode();
        this.status = errorCode.getStatus();
        this.responseTime = ZonedDateTime.now();
    }
}