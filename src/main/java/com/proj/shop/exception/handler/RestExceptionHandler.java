package com.proj.shop.exception.handler;


import com.proj.shop.constant.ErrorCode;
import com.proj.shop.exception.ErrorResponse;
import com.proj.shop.exception.SiteException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleRuntimeException(RuntimeException ex){

        ErrorResponse response = new ErrorResponse("알 수 없는 에러가 발생했습니다.", ErrorCode.UNKNOWN_EXCEPTION);

        log.error("error", ex);

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(SiteException.class)
    public ResponseEntity<ErrorResponse> methodSiteException(SiteException ex){

        ErrorResponse response = new ErrorResponse(ex.getMessage(), ex.getErrorCode());

        log.error("error", ex);

        return new ResponseEntity<>(response, HttpStatus.valueOf(ex.getErrorCode().getCode()));
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        // 에러 메세지 문자열 가공 field : message 형태
        List<String> errorList = ex
                .getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError -> fieldError.getField() + " : " +  fieldError.getDefaultMessage())
                .collect(Collectors.toList());

        log.error("error", ex);

        ErrorResponse response = new ErrorResponse(errorList.toString(), ErrorCode.VALIDATION_EXCEPTION);

        return new ResponseEntity(response, HttpStatus.valueOf(ErrorCode.VALIDATION_EXCEPTION.getStatus()));
    }
}
