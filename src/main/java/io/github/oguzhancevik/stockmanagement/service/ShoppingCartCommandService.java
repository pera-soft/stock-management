package io.github.oguzhancevik.stockmanagement.service;

import io.github.oguzhancevik.stockmanagement.model.request.ShoppingRequest;
import io.github.oguzhancevik.stockmanagement.model.response.ApiResponse;

public interface ShoppingCartCommandService {
    ApiResponse shopping(ShoppingRequest request);
}
