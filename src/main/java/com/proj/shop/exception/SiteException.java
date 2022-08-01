package com.proj.shop.exception;

import com.proj.shop.constant.ErrorCode;
import lombok.Getter;

@Getter
public class SiteException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    private ErrorCode errorCode;

    public SiteException(String message, ErrorCode errorCode){
        super(message);
        this.errorCode = errorCode;
    }


}