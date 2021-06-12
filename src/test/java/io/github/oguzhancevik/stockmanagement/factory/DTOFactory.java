package io.github.oguzhancevik.stockmanagement.factory;

import io.github.oguzhancevik.stockmanagement.model.request.CategoryRequest;
import io.github.oguzhancevik.stockmanagement.model.response.CategoryDTO;

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

}
