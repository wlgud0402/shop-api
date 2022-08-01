package com.proj.shop.controller;

import com.proj.shop.model.api.request.BrandAddRequest;
import com.proj.shop.model.api.request.BrandEditRequest;
import com.proj.shop.model.api.response.OkResponse;
import com.proj.shop.service.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping(value="/api/brand")
public class BrandController {
    private final BrandService brandService;

    // 브랜드 추가
    @PostMapping("")
    public OkResponse addBrand(
            @Valid @RequestBody BrandAddRequest brandAddRequest
    ){
        brandService.addBrand(brandAddRequest);
        return new OkResponse();
    }

    // 브랜드 수정
    @PutMapping("{id}")
    public OkResponse editBrand(
            @Valid @RequestBody BrandEditRequest brandEditRequest,
            @PathVariable("id") Long id
    ){
        brandService.editBrand(id, brandEditRequest);
        return new OkResponse();
    }

    // 브랜드 삭제
    @DeleteMapping("{id}")
    public OkResponse deleteBrand(
            @PathVariable("id") Long id
    ){
        brandService.deleteBrand(id);
        return new OkResponse();
    }
}
