package com.proj.shop.controller;


import com.proj.shop.model.api.response.MinAllBrandAllCategoryResponse;
import com.proj.shop.model.api.response.MinMaxBrandByCategoryNameResponse;
import com.proj.shop.model.api.response.MinOneBrandAllCategoryResponse;
import com.proj.shop.service.ProductSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping(value="/api/search")
@Validated
public class ProductSearchController {
    private final ProductSearchService productSearchService;

    // 모든 카테고리의 상품을 브랜드 별로 자유롭게 선택해서 모든 상품을 구매할 때 최저가조회 API
    @GetMapping("/min/allBrand/allCategory")
    public MinAllBrandAllCategoryResponse minAllBrandAllCategory(){
        MinAllBrandAllCategoryResponse minAllBrandAllCategoryResponse = productSearchService.minAllBrandAllCategory();
        return minAllBrandAllCategoryResponse;
    }

    // 한 브랜드에서 모든 카테고리의 상품을 한꺼번에 구매할 경우 최저가 및 브랜드 조회 API
    @GetMapping("/min/oneBrand/allCategory")
    public MinOneBrandAllCategoryResponse minOneBrandAllCategory(){
        MinOneBrandAllCategoryResponse minOneBrandAllCategoryResponse = productSearchService.minOneBrandAllCategory();
        return minOneBrandAllCategoryResponse;
    }

    // 각 카테고리 이름으로 최소,최대 가격 조회 API
    @GetMapping("/minMaxBrandByCategoryName")
    public MinMaxBrandByCategoryNameResponse searchMinMaxBrandByCategoryName(
            @RequestParam("categoryName") String categoryName
    ){
        MinMaxBrandByCategoryNameResponse minMaxBrandByCategoryNameResponse= productSearchService.minMaxBrandByCategoryName(categoryName);
        return minMaxBrandByCategoryNameResponse;
    }
}
