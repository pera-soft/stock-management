package io.github.oguzhancevik.stockmanagement.service.impl;

import io.github.oguzhancevik.stockmanagement.model.entity.Category;
import io.github.oguzhancevik.stockmanagement.model.exception.BusinessValidationException;
import io.github.oguzhancevik.stockmanagement.model.response.CategoryDTO;
import io.github.oguzhancevik.stockmanagement.repository.CategoryRepository;
import io.github.oguzhancevik.stockmanagement.service.CategoryQueryService;
import io.github.oguzhancevik.stockmanagement.util.BaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static io.github.oguzhancevik.stockmanagement.model.exception.BusinessValidationRule.CATEGORY_NOT_FOUND;

@Service
public class CategoryQueryServiceImpl implements CategoryQueryService {

    private final CategoryRepository repository;

    @Autowired
    public CategoryQueryServiceImpl(CategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CategoryDTO> findCategories() {
        List<Category> categories = repository.findAll();
        return BaseMapper.INSTANCE.toDTO(categories);
    }

    @Override
    @Transactional(readOnly = true)
    public CategoryDTO findById(Long categoryId) {
        Category category = repository.findById(categoryId)
                .orElseThrow(() -> new BusinessValidationException(CATEGORY_NOT_FOUND));
        return BaseMapper.INSTANCE.toDTO(category);
    }

}
