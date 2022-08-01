package com.proj.shop.model.dto;

import lombok.Data;

@Data
public class BrandPrice {
    private String brandName;
    private Long price;

    public BrandPrice(String brandName, Long price) {
        this.brandName = brandName;
        this.price = price;
    }
}
