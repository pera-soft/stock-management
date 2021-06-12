package io.github.oguzhancevik.stockmanagement.factory;

import io.github.oguzhancevik.stockmanagement.model.entity.Category;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class EntityFactory {

    public Category category() {
        Category category = new Category();
        category.setId(1l);
        category.setCreatedDate(new Date());
        category.setName("Snacks");
        return category;
    }

    public List<Category> categories() {
        return Arrays.asList(category());
    }

}
