package io.github.oguzhancevik.stockmanagement.service;

import io.github.oguzhancevik.stockmanagement.model.request.ShoppingRequest;

public interface ShoppingCartCommandService {
    void shopping(ShoppingRequest request);
}
