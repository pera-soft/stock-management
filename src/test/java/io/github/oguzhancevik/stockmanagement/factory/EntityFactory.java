package io.github.oguzhancevik.stockmanagement.factory;

import io.github.oguzhancevik.stockmanagement.model.entity.Category;
import io.github.oguzhancevik.stockmanagement.model.entity.SubCategory;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class EntityFactory {

    public Category category() {
        Category category = new Category();
        category.setId(1L);
        category.setCreatedDate(new Date());
        category.setName("Snacks");
        return category;
    }

    public List<Category> categories() {
        return Arrays.asList(category());
    }

    public SubCategory subCategory() {
        SubCategory subCategory = new SubCategory();
        subCategory.setId(15L);
        subCategory.setName("Chips");
        subCategory.setCategory(category());
        return subCategory;
    }

    public List<SubCategory> subCategories() {
        return Arrays.asList(subCategory());
    }

}
