package com.proj.shop.service;

import com.proj.shop.constant.ErrorCode;
import com.proj.shop.exception.SiteException;
import com.proj.shop.model.api.response.MinAllBrandAllCategoryResponse;
import com.proj.shop.model.api.response.MinMaxBrandByCategoryNameResponse;
import com.proj.shop.model.api.response.MinOneBrandAllCategoryResponse;
import com.proj.shop.model.dto.BrandPrice;
import com.proj.shop.model.dto.MinAllBrandAllCategory;
import com.proj.shop.model.dto.MinOneBrandTotalAndBrand;
import com.proj.shop.model.entity.Brand;
import com.proj.shop.model.entity.Category;
import com.proj.shop.model.entity.Product;
import com.proj.shop.repository.BrandRepository;
import com.proj.shop.repository.CategoryRepository;
import com.proj.shop.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProductSearchService {
    private final BrandRepository brandRepository;
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public MinAllBrandAllCategoryResponse minAllBrandAllCategory(){
        // 상품 카테고리명으로 HashMap 생성을 통해 같은 카테고리 상품의 최저가 갱신
        HashMap<String, MinAllBrandAllCategory> minAllBrandAllCategoryFiltered = new HashMap<>();
        List<MinAllBrandAllCategory> minAllBrandAllCategoryList = productRepository.findMinAllBrandAllCategory();
        minAllBrandAllCategoryList.forEach(minAllBrandAllCategory -> {
            String categoryName = minAllBrandAllCategory.getCategoryName();
            if(minAllBrandAllCategoryFiltered.containsKey(categoryName)){
                if(minAllBrandAllCategoryFiltered.get(categoryName).getPrice() > minAllBrandAllCategory.getPrice()){
                    minAllBrandAllCategoryFiltered.put(categoryName, minAllBrandAllCategory);
                }
            }else{
                minAllBrandAllCategoryFiltered.put(categoryName, minAllBrandAllCategory);
            }
        });

        // 생성된 HashMap 을 통해 return 값 생성
        List<MinAllBrandAllCategory> minAllBrandAllCategoryFilteredList = new ArrayList<>();
        Long totalPrice = 0L;
        for(String categoryName : minAllBrandAllCategoryFiltered.keySet()){
            minAllBrandAllCategoryFilteredList.add(minAllBrandAllCategoryFiltered.get(categoryName));
            totalPrice += minAllBrandAllCategoryFiltered.get(categoryName).getPrice();
        }

        MinAllBrandAllCategoryResponse minAllBrandAllCategoryResponse = new MinAllBrandAllCategoryResponse(minAllBrandAllCategoryFilteredList, totalPrice);
        return minAllBrandAllCategoryResponse;
    }


    public MinOneBrandAllCategoryResponse minOneBrandAllCategory() {
        MinOneBrandTotalAndBrand minOneBrandTotalAndBrand = productRepository.findMinOneBrandAllCategory();
        Brand brand = brandRepository.findById(minOneBrandTotalAndBrand.getBrandId())
                .orElseThrow(()-> new SiteException("브랜드가 존재하지 않습니다.", ErrorCode.BRAND_NOT_FOUND_EXCEPTION));

        String brandName = brand.getName();
        MinOneBrandAllCategoryResponse minOneBrandAllCategoryResponse = new MinOneBrandAllCategoryResponse(brandName, minOneBrandTotalAndBrand.getTotal());
        return minOneBrandAllCategoryResponse;
    }

    public MinMaxBrandByCategoryNameResponse minMaxBrandByCategoryName(String categoryName) {
        Category category = categoryRepository.findByName(categoryName)
                .orElseThrow(()-> new SiteException("카테고리가 존재하지 않습니다.", ErrorCode.CATEGORY_NOT_FOUND_EXCEPTION));

        Product maxPriceProduct = productRepository.findTopByCategoryIdAndDeletedAtIsNullOrderByPriceDesc(category.getId())
                .orElseThrow(()-> new SiteException("상품이 존재하지 않습니다.", ErrorCode.PRODUCT_NOT_FOUND_EXCEPTION));

        Product minPriceProduct = productRepository.findTopByCategoryIdAndDeletedAtIsNullOrderByPrice(category.getId())
                .orElseThrow(()-> new SiteException("상품이 존재하지 않습니다.", ErrorCode.PRODUCT_NOT_FOUND_EXCEPTION));

        BrandPrice minBrandPrice = new BrandPrice(minPriceProduct.getBrand().getName(),minPriceProduct.getPrice());
        BrandPrice maxBrandPrice = new BrandPrice(maxPriceProduct.getBrand().getName(),maxPriceProduct.getPrice());

        MinMaxBrandByCategoryNameResponse minMaxBrandByCategoryNameResponse = new MinMaxBrandByCategoryNameResponse(minBrandPrice, maxBrandPrice);
        return minMaxBrandByCategoryNameResponse;
    }

}
