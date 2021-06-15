package io.github.oguzhancevik.stockmanagement.factory;

import io.github.oguzhancevik.stockmanagement.model.request.CategoryRequest;
import io.github.oguzhancevik.stockmanagement.model.request.ProductRequest;
import io.github.oguzhancevik.stockmanagement.model.request.ShoppingRequest;
import io.github.oguzhancevik.stockmanagement.model.request.SubCategoryRequest;
import io.github.oguzhancevik.stockmanagement.model.response.CategoryDTO;
import io.github.oguzhancevik.stockmanagement.model.response.ProductDTO;
import io.github.oguzhancevik.stockmanagement.model.response.SubCategoryDTO;

import java.math.BigDecimal;
import java.util.Date;

public class DTOFactory {

    public CategoryDTO category() {
        var category = new CategoryDTO();
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
        var subCategory = new SubCategoryDTO();
        subCategory.setId(15L);
        subCategory.setName("Chips");
        subCategory.setCategory(category());
        return subCategory;
    }

    public ProductRequest productRequest() {
        var request = new ProductRequest();
        request.setName("Pringles Original");
        request.setPrice(BigDecimal.valueOf(16.29));
        request.setStockAmount(375L);
        request.setSubCategoryId(subCategory().getId());
        return request;
    }

    public ProductDTO product() {
        var product = new ProductDTO();
        product.setId(50L);
        product.setName("Pringles Original");
        product.setPrice(BigDecimal.valueOf(16.29));
        product.setStockAmount(375L);
        product.setSubCategory(subCategory());
        return product;
    }

    public ShoppingRequest shoppingRequest() {
        return new ShoppingRequest(product().getId());
    }

}
