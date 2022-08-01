package com.proj.shop.service;

import com.proj.shop.constant.ErrorCode;
import com.proj.shop.exception.SiteException;
import com.proj.shop.model.api.request.ProductAddRequest;
import com.proj.shop.model.api.request.ProductEditRequest;
import com.proj.shop.model.entity.Brand;
import com.proj.shop.model.entity.Category;
import com.proj.shop.model.entity.Product;
import com.proj.shop.repository.BrandRepository;
import com.proj.shop.repository.CategoryRepository;
import com.proj.shop.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductService {
    private final ProductRepository productRepository;
    private final BrandRepository brandRepository;
    private final CategoryRepository categoryRepository;

    @Transactional
    public void deleteProduct(Long id) {
        Product product = productRepository.findByIdAndDeletedAtIsNull(id)
                .orElseThrow(()->new SiteException("상품이 존재하지 않습니다.", ErrorCode.PRODUCT_NOT_FOUND_EXCEPTION));
        product.deleteProduct();
    }


    @Transactional
    public void addProduct(ProductAddRequest productAddRequest) {
        Brand brand = brandRepository.findByIdAndDeletedAtIsNull(productAddRequest.getBrandId())
                .orElseThrow(()-> new SiteException("브랜드가 존재하지 않습니다.", ErrorCode.BRAND_NOT_FOUND_EXCEPTION));

        Category category = categoryRepository.findById(productAddRequest.getCategoryId())
                .orElseThrow(()-> new SiteException("카테고리가 존재하지 않습니다.", ErrorCode.CATEGORY_NOT_FOUND_EXCEPTION));

        Product product = Product.builder()
                .name(productAddRequest.getName())
                .price(productAddRequest.getPrice())
                .category(category)
                .brand(brand)
                .build();
        productRepository.save(product);
    }

    @Transactional
    public void editProduct(Long id, ProductEditRequest productEditRequest) {
        Product product = productRepository.findByIdAndDeletedAtIsNull(id)
                .orElseThrow(()->new SiteException("상품이 존재하지 않습니다.", ErrorCode.PRODUCT_NOT_FOUND_EXCEPTION));

        Brand brand = brandRepository.findByIdAndDeletedAtIsNull(productEditRequest.getBrandId())
                .orElseThrow(()-> new SiteException("브랜드가 존재하지 않습니다.", ErrorCode.BRAND_NOT_FOUND_EXCEPTION));

        Category category = categoryRepository.findById(productEditRequest.getCategoryId())
                .orElseThrow(()-> new SiteException("카테고리가 존재하지 않습니다.", ErrorCode.CATEGORY_NOT_FOUND_EXCEPTION));

        product.editProduct(productEditRequest, brand, category);
    }
}
