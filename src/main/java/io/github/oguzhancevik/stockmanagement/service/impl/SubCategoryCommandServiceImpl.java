package io.github.oguzhancevik.stockmanagement.service.impl;

import io.github.oguzhancevik.stockmanagement.model.entity.Category;
import io.github.oguzhancevik.stockmanagement.model.entity.SubCategory;
import io.github.oguzhancevik.stockmanagement.model.exception.BusinessValidationException;
import io.github.oguzhancevik.stockmanagement.model.request.SubCategoryRequest;
import io.github.oguzhancevik.stockmanagement.model.response.SubCategoryDTO;
import io.github.oguzhancevik.stockmanagement.repository.CategoryRepository;
import io.github.oguzhancevik.stockmanagement.repository.SubCategoryRepository;
import io.github.oguzhancevik.stockmanagement.service.SubCategoryCommandService;
import io.github.oguzhancevik.stockmanagement.util.BaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static io.github.oguzhancevik.stockmanagement.model.exception.BusinessValidationRule.CATEGORY_NOT_FOUND;
import static io.github.oguzhancevik.stockmanagement.model.exception.BusinessValidationRule.SUB_CATEGORY_NOT_FOUND;

@Service
public class SubCategoryCommandServiceImpl implements SubCategoryCommandService {

    private final SubCategoryRepository subCategoryRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public SubCategoryCommandServiceImpl(SubCategoryRepository subCategoryRepository, CategoryRepository categoryRepository) {
        this.subCategoryRepository = subCategoryRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    @Transactional
    public SubCategoryDTO create(SubCategoryRequest request) {
        SubCategory subCategory = new SubCategory(request.getName());
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new BusinessValidationException(CATEGORY_NOT_FOUND));
        subCategory.setCategory(category);
        subCategoryRepository.save(subCategory);
        return BaseMapper.INSTANCE.toDTO(subCategory);
    }

    @Override
    @Transactional
    public SubCategoryDTO update(Long subCategoryId, SubCategoryRequest request) {
        SubCategory subCategory = subCategoryRepository.findById(subCategoryId)
                .orElseThrow(() -> new BusinessValidationException(SUB_CATEGORY_NOT_FOUND));
        subCategory.setName(request.getName());
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new BusinessValidationException(CATEGORY_NOT_FOUND));
        subCategory.setCategory(category);
        return BaseMapper.INSTANCE.toDTO(subCategory);
    }

    @Override
    @Transactional
    public void deleteById(Long subCategoryId) {
        subCategoryRepository.deleteById(subCategoryId);
    }

}
