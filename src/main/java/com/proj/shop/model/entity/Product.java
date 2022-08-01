package com.proj.shop.model.entity;

import com.proj.shop.model.api.request.ProductEditRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product")
@Data
public class Product extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Long price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @Column(name = "deleted_at")
    private ZonedDateTime deletedAt;
    public void deleteProduct(){
        this.deletedAt = ZonedDateTime.now();
    }

    public void editProduct(ProductEditRequest productEditRequest, Brand brand, Category category) {
        this.name = productEditRequest.getName();
        this.price = productEditRequest.getPrice();
        this.category = category;
        this.brand = brand;
    }
}

