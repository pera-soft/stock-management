package io.github.oguzhancevik.stockmanagement.repository;

import io.github.oguzhancevik.stockmanagement.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findBySubCategoryCategoryId(Long categoryId);

    List<Product> findBySubCategoryId(Long subCategoryId);

    List<Product> findByNameContainingIgnoreCase(String name);

    List<Product> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice);
}
