package com.proj.shop.service;

import com.proj.shop.constant.ErrorCode;
import com.proj.shop.exception.SiteException;
import com.proj.shop.model.api.request.BrandAddRequest;
import com.proj.shop.model.api.request.BrandEditRequest;
import com.proj.shop.model.entity.Brand;
import com.proj.shop.repository.BrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BrandService {
    private final BrandRepository brandRepository;

    @Transactional
    public void deleteBrand(Long id) {
        Brand brand = brandRepository.findByIdAndDeletedAtIsNull(id)
                .orElseThrow(()-> new SiteException("브랜드가 존재하지 않습니다.", ErrorCode.BRAND_NOT_FOUND_EXCEPTION));
        brand.deleteBrand();
    }

    @Transactional
    public void addBrand(BrandAddRequest brandAddRequest) {
        Brand brand = Brand.builder()
                .name(brandAddRequest.getName())
                .build();
        brandRepository.save(brand);
    }

    @Transactional
    public void editBrand(Long id, BrandEditRequest brandEditRequest) {
        Brand brand = brandRepository.findByIdAndDeletedAtIsNull(id)
                .orElseThrow(()-> new SiteException("브랜드가 존재하지 않습니다.", ErrorCode.BRAND_NOT_FOUND_EXCEPTION));
        brand.editBrand(brandEditRequest);

    }
}
