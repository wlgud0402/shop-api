package com.proj.shop.controller;

import com.proj.shop.model.api.request.ProductAddRequest;
import com.proj.shop.model.api.request.ProductEditRequest;
import com.proj.shop.model.api.response.OkResponse;
import com.proj.shop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping(value="/api/product")
public class ProductController {
    private final ProductService productService;

    // 상품 추가
    @PostMapping("")
    public OkResponse addProduct(
            @Valid @RequestBody ProductAddRequest productAddRequest
            ){
        productService.addProduct(productAddRequest);
        return new OkResponse();
    }

    // 상품 수정
    @PutMapping("{id}")
    public OkResponse editProduct(
            @Valid @RequestBody ProductEditRequest productEditRequest,
            @PathVariable("id") Long id
    ){
        productService.editProduct(id,productEditRequest);
        return new OkResponse();
    }

    // 상품 삭제
    @DeleteMapping("{id}")
    public OkResponse deleteProduct(
            @PathVariable("id") Long id
    ){
        productService.deleteProduct(id);
        return new OkResponse();
    }


}
