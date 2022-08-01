package com.proj.shop.repository;

import com.proj.shop.model.dto.MinAllBrandAllCategory;
import com.proj.shop.model.dto.MinOneBrandTotalAndBrand;
import com.proj.shop.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findTopByCategoryIdAndDeletedAtIsNullOrderByPriceDesc(Long id);

    Optional<Product> findTopByCategoryIdAndDeletedAtIsNullOrderByPrice(Long id);

    @Query(value = "select sum(min_price) as total, brand_id as brandId from (select brand_id, category_id, min(price) as min_price, deleted_at from product group by brand_id, category_id, deleted_at having deleted_at is null) as t group by brand_id order by total limit 1",nativeQuery=true)
    MinOneBrandTotalAndBrand findMinOneBrandAllCategory();

    @Query(value = "select b.name as brandName, c.name as categoryName, min(p.price) as price from product as p inner join brand as b on b.id = p.brand_id inner join category as c on c.id = p.category_id group by p.category_id, p.brand_id, p.deleted_at having p.deleted_at is null", nativeQuery = true)
    List<MinAllBrandAllCategory> findMinAllBrandAllCategory();

    Optional<Product> findByIdAndDeletedAtIsNull(Long id);
}
