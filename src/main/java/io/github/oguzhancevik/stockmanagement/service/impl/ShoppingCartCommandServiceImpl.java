package io.github.oguzhancevik.stockmanagement.service.impl;

import io.github.oguzhancevik.stockmanagement.model.entity.Product;
import io.github.oguzhancevik.stockmanagement.model.exception.BusinessValidationException;
import io.github.oguzhancevik.stockmanagement.model.request.ShoppingRequest;
import io.github.oguzhancevik.stockmanagement.repository.ProductRepository;
import io.github.oguzhancevik.stockmanagement.service.ShoppingCartCommandService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static io.github.oguzhancevik.stockmanagement.model.exception.BusinessValidationRule.INVALID_TRANSACTION;

@Service
public class ShoppingCartCommandServiceImpl implements ShoppingCartCommandService {

    private final ProductRepository productRepository;

    public ShoppingCartCommandServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    @Transactional
    public void shopping(ShoppingRequest request) {
        Product product = productRepository.findProductById(request.getProductId())
                .orElseThrow(() -> new BusinessValidationException(INVALID_TRANSACTION));
        product.setStockAmount(product.getStockAmount() - 1);
    }

}
