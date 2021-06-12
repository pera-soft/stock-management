package io.github.oguzhancevik.stockmanagement.repository;

import io.github.oguzhancevik.stockmanagement.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
