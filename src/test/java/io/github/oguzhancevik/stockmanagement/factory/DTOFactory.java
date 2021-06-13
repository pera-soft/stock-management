package io.github.oguzhancevik.stockmanagement.factory;

import io.github.oguzhancevik.stockmanagement.model.request.CategoryRequest;
import io.github.oguzhancevik.stockmanagement.model.request.SubCategoryRequest;
import io.github.oguzhancevik.stockmanagement.model.response.CategoryDTO;
import io.github.oguzhancevik.stockmanagement.model.response.SubCategoryDTO;

import java.util.Date;

public class DTOFactory {

    public CategoryDTO category() {
        CategoryDTO category = new CategoryDTO();
        category.setId(1L);
        category.setCreatedDate(new Date());
        category.setName("Snacks");
        return category;
    }

    public CategoryRequest categoryRequest() {
        return new CategoryRequest("Vegetables");
    }

    public SubCategoryRequest subCategoryRequest() {
        return new SubCategoryRequest(category().getId(), "Soup");
    }

    public SubCategoryDTO subCategory() {
        SubCategoryDTO subCategory = new SubCategoryDTO();
        subCategory.setId(15L);
        subCategory.setName("Chips");
        subCategory.setCategory(category());
        return subCategory;
    }

}
