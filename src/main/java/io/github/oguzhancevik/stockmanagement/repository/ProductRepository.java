package io.github.oguzhancevik.stockmanagement.repository;

import io.github.oguzhancevik.stockmanagement.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
