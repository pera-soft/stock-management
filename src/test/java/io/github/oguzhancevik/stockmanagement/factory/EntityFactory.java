package io.github.oguzhancevik.stockmanagement.factory;

import io.github.oguzhancevik.stockmanagement.model.entity.Category;
import io.github.oguzhancevik.stockmanagement.model.entity.Product;
import io.github.oguzhancevik.stockmanagement.model.entity.SubCategory;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class EntityFactory {

    public Category category() {
        var category = new Category();
        category.setId(1L);
        category.setCreatedDate(new Date());
        category.setName("Snacks");
        return category;
    }

    public List<Category> categories() {
        return Collections.singletonList(category());
    }

    public SubCategory subCategory() {
        var subCategory = new SubCategory();
        subCategory.setId(15L);
        subCategory.setName("Chips");
        subCategory.setCategory(category());
        return subCategory;
    }

    public List<SubCategory> subCategories() {
        return Collections.singletonList(subCategory());
    }

    public Product product() {
        var product = new Product();
        product.setName("Pringles Original");
        product.setPrice(BigDecimal.valueOf(16.29));
        product.setStockAmount(375L);
        product.setSubCategory(subCategory());
        return product;
    }

    public List<Product> products() {
        return Collections.singletonList(product());
    }

}
