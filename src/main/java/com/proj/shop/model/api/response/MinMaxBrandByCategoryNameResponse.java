package com.proj.shop.model.api.response;

import com.proj.shop.model.dto.BrandPrice;
import lombok.Getter;



@Getter
public class MinMaxBrandByCategoryNameResponse {
    public BrandPrice min;
    public BrandPrice max;

    public MinMaxBrandByCategoryNameResponse(BrandPrice min, BrandPrice max) {
        this.min = min;
        this.max = max;
    }
}
