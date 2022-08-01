package com.proj.shop.model.entity;

import com.proj.shop.model.api.request.BrandEditRequest;
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
@Table(name = "brand")
@Data
public class Brand extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "deleted_at")
    private ZonedDateTime deletedAt;

    public void deleteBrand(){
        this.deletedAt = ZonedDateTime.now();
    }

    public void editBrand(BrandEditRequest brandEditRequest) {
        this.name = brandEditRequest.getName();
    }
}
