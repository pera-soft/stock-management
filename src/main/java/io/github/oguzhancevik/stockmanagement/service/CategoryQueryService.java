package io.github.oguzhancevik.stockmanagement.service;

import io.github.oguzhancevik.stockmanagement.model.request.CategoryRequest;
import io.github.oguzhancevik.stockmanagement.model.response.CategoryDTO;

import java.util.List;

public interface CategoryQueryService {
    List<CategoryDTO> findCategories();
    CategoryDTO findById(Long categoryId);
    CategoryDTO create(CategoryRequest request);
    CategoryDTO update(Long categoryId, CategoryRequest request);
    void deleteById(Long categoryId);
}
