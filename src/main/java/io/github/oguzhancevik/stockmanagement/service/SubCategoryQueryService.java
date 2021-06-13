package io.github.oguzhancevik.stockmanagement.service;

import io.github.oguzhancevik.stockmanagement.model.response.SubCategoryDTO;

import java.util.List;

public interface SubCategoryQueryService {
    List<SubCategoryDTO> findSubCategories();

    SubCategoryDTO findById(Long subCategoryId);
}
