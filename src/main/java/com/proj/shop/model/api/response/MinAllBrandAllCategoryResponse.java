package com.proj.shop.model.api.response;

import com.proj.shop.model.dto.MinAllBrandAllCategory;
import lombok.Getter;

import java.util.List;

@Getter
public class MinAllBrandAllCategoryResponse {
    private List<MinAllBrandAllCategory> items;
    private Long totalPrice;

    public MinAllBrandAllCategoryResponse(List<MinAllBrandAllCategory> items, Long totalPrice) {
        this.items = items;
        this.totalPrice = totalPrice;
    }
}
