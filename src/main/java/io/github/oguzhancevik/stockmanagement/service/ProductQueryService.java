package io.github.oguzhancevik.stockmanagement.service;

import io.github.oguzhancevik.stockmanagement.model.response.ProductDTO;

import java.math.BigDecimal;
import java.util.List;

public interface ProductQueryService {
    List<ProductDTO> findProducts();

    ProductDTO findById(Long productId);

    List<ProductDTO> findProductsByCategoryId(Long categoryId);

    List<ProductDTO> findProductsBySubCategoryId(Long subCategoryId);

    List<ProductDTO> findProductsByName(String name);

    List<ProductDTO> findProductsByPrice(BigDecimal minPrice, BigDecimal maxPrice);
}
