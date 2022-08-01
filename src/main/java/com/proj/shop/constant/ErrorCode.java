package com.proj.shop.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    /* 공통 */
    SITE_EXCEPTION(400, "SITE_EXCEPTION"),
    PRODUCT_NOT_FOUND_EXCEPTION(404, "PRODUCT_EXCEPTION"),
    BRAND_NOT_FOUND_EXCEPTION(404, "BRAND_EXCEPTION"),
    CATEGORY_NOT_FOUND_EXCEPTION(404,"CATEGORY_EXCEPTION"),
    VALIDATION_EXCEPTION(400,  "VALIDATION_ERROR"),
    UNKNOWN_EXCEPTION(500, "UNKNOWN_EXCEPTION")
    ;

    private int code;
    private String status;
}
