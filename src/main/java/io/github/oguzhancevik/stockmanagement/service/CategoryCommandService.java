package io.github.oguzhancevik.stockmanagement.service;

import io.github.oguzhancevik.stockmanagement.model.request.CategoryRequest;
import io.github.oguzhancevik.stockmanagement.model.response.CategoryDTO;

public interface CategoryCommandService {
    CategoryDTO create(CategoryRequest request);

    CategoryDTO update(Long categoryId, CategoryRequest request);

    void deleteById(Long categoryId);
}
