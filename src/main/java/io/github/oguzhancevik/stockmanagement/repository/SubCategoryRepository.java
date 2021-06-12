package io.github.oguzhancevik.stockmanagement.repository;

import io.github.oguzhancevik.stockmanagement.model.entity.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubCategoryRepository extends JpaRepository<SubCategory, Long> {
}
