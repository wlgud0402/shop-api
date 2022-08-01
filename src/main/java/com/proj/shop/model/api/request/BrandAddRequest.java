package com.proj.shop.model.api.request;

import lombok.Getter;

import javax.validation.constraints.NotBlank;


@Getter
public class BrandAddRequest {
    @NotBlank
    private String name;
}
