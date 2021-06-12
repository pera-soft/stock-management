package io.github.oguzhancevik.stockmanagement.factory;

import io.github.oguzhancevik.stockmanagement.model.response.CategoryDTO;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class DTOFactory {

    public CategoryDTO category() {
        CategoryDTO category = new CategoryDTO();
        category.setId(1l);
        category.setCreatedDate(new Date());
        category.setName("Snacks");
        return category;
    }

    public List<CategoryDTO> categories() {
        return Arrays.asList(category());
    }

}
