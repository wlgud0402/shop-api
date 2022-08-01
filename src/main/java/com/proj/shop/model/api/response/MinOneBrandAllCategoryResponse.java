package com.proj.shop.model.api.response;

import lombok.Getter;

@Getter
public class MinOneBrandAllCategoryResponse {
    private String brandName;
    private Long totalPrice;

    public MinOneBrandAllCategoryResponse(String brandName, Long totalPrice) {
        this.brandName = brandName;
        this.totalPrice = totalPrice;
    }
}
