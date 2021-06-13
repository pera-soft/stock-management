package io.github.oguzhancevik.stockmanagement.factory;

import io.github.oguzhancevik.stockmanagement.model.request.CategoryRequest;
import io.github.oguzhancevik.stockmanagement.model.request.ProductRequest;
import io.github.oguzhancevik.stockmanagement.model.request.SubCategoryRequest;
import io.github.oguzhancevik.stockmanagement.model.response.CategoryDTO;
import io.github.oguzhancevik.stockmanagement.model.response.ProductDTO;
import io.github.oguzhancevik.stockmanagement.model.response.SubCategoryDTO;

import java.math.BigDecimal;
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

    public ProductRequest productRequest() {
        ProductRequest request = new ProductRequest();
        request.setName("Pringles Original");
        request.setPrice(BigDecimal.valueOf(16.29));
        request.setStockAmount(375L);
        request.setSubCategoryId(subCategory().getId());
        return request;
    }

    public ProductDTO product() {
        ProductDTO product = new ProductDTO();
        product.setName("Pringles Original");
        product.setPrice(BigDecimal.valueOf(16.29));
        product.setStockAmount(375L);
        product.setSubCategory(subCategory());
        return product;
    }

}
