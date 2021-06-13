package io.github.oguzhancevik.stockmanagement.service;

import io.github.oguzhancevik.stockmanagement.model.request.SubCategoryRequest;
import io.github.oguzhancevik.stockmanagement.model.response.SubCategoryDTO;

public interface SubCategoryCommandService {
    SubCategoryDTO create(SubCategoryRequest request);

    SubCategoryDTO update(Long subCategoryId, SubCategoryRequest request);

    void deleteById(Long subCategoryId);
}
