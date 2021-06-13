package io.github.oguzhancevik.stockmanagement.service.impl;

import io.github.oguzhancevik.stockmanagement.model.entity.SubCategory;
import io.github.oguzhancevik.stockmanagement.model.exception.BusinessValidationException;
import io.github.oguzhancevik.stockmanagement.model.response.SubCategoryDTO;
import io.github.oguzhancevik.stockmanagement.repository.SubCategoryRepository;
import io.github.oguzhancevik.stockmanagement.service.SubCategoryQueryService;
import io.github.oguzhancevik.stockmanagement.util.BaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static io.github.oguzhancevik.stockmanagement.model.exception.BusinessValidationRule.SUB_CATEGORY_NOT_FOUND;

@Service
public class SubCategoryQueryServiceImpl implements SubCategoryQueryService {

    private final SubCategoryRepository repository;

    @Autowired
    public SubCategoryQueryServiceImpl(SubCategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<SubCategoryDTO> findSubCategories() {
        List<SubCategory> subCategories = repository.findAll();
        List<SubCategoryDTO> dtoList = new ArrayList<>(subCategories.size());
        for (SubCategory subCategory : subCategories) {
            dtoList.add(BaseMapper.INSTANCE.toDTO(subCategory));
        }
        return dtoList;
    }

    @Override
    @Transactional(readOnly = true)
    public SubCategoryDTO findById(Long categoryId) {
        SubCategory subCategory = repository.findById(categoryId)
                .orElseThrow(() -> new BusinessValidationException(SUB_CATEGORY_NOT_FOUND));
        return BaseMapper.INSTANCE.toDTO(subCategory);
    }

}
