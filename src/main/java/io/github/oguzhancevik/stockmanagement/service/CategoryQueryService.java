package io.github.oguzhancevik.stockmanagement.service;

import io.github.oguzhancevik.stockmanagement.model.response.CategoryDTO;

import java.util.List;

public interface CategoryQueryService {
    List<CategoryDTO> findCategories();

    CategoryDTO findById(Long categoryId);
}
