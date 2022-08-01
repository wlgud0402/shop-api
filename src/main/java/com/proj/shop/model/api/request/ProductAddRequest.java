package com.proj.shop.model.api.request;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Getter
public class ProductAddRequest {
    @NotBlank
    private String name;

    @NotNull
    private Long price;

    @NotNull
    private Long categoryId;

    @NotNull
    private Long brandId;
}
