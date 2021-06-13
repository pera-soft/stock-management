package io.github.oguzhancevik.stockmanagement.service;

import io.github.oguzhancevik.stockmanagement.model.request.ProductRequest;
import io.github.oguzhancevik.stockmanagement.model.response.ProductDTO;

public interface ProductCommandService {
    ProductDTO create(ProductRequest request);

    ProductDTO update(Long productId, ProductRequest request);

    void deleteById(Long productId);
}
