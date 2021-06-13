package io.github.oguzhancevik.stockmanagement.repository;

import io.github.oguzhancevik.stockmanagement.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<List<Product>> findBySubCategoryCategoryId(Long categoryId);

    Optional<List<Product>> findBySubCategoryId(Long subCategoryId);

    Optional<List<Product>> findByNameContainingIgnoreCase(String name);

    Optional<List<Product>> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice);

    @Lock(LockModeType.PESSIMISTIC_READ)
    Optional<Product> findProductById(Long id);
}
