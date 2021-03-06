package io.github.oguzhancevik.stockmanagement.service.impl;

import io.github.oguzhancevik.stockmanagement.model.entity.Category;
import io.github.oguzhancevik.stockmanagement.model.exception.BusinessValidationException;
import io.github.oguzhancevik.stockmanagement.model.request.CategoryRequest;
import io.github.oguzhancevik.stockmanagement.model.response.CategoryDTO;
import io.github.oguzhancevik.stockmanagement.repository.CategoryRepository;
import io.github.oguzhancevik.stockmanagement.service.CategoryCommandService;
import io.github.oguzhancevik.stockmanagement.util.BaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static io.github.oguzhancevik.stockmanagement.model.exception.BusinessValidationRule.CATEGORY_NOT_FOUND;

@Service
public class CategoryCommandServiceImpl implements CategoryCommandService {

    private final CategoryRepository repository;

    @Autowired
    public CategoryCommandServiceImpl(CategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public CategoryDTO create(CategoryRequest request) {
        var category = new Category(request.getName());
        repository.save(category);
        return BaseMapper.INSTANCE.toDTO(category);
    }

    @Override
    @Transactional
    public CategoryDTO update(Long categoryId, CategoryRequest request) {
        Category category = repository.findById(categoryId)
                .orElseThrow(() -> new BusinessValidationException(CATEGORY_NOT_FOUND));
        category.setName(request.getName());
        return BaseMapper.INSTANCE.toDTO(category);
    }

    @Override
    @Transactional
    public void deleteById(Long categoryId) {
        repository.deleteById(categoryId);
    }

}
