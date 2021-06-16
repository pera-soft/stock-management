package io.github.oguzhancevik.stockmanagement.service.impl;

import io.github.oguzhancevik.stockmanagement.model.entity.Product;
import io.github.oguzhancevik.stockmanagement.model.entity.SubCategory;
import io.github.oguzhancevik.stockmanagement.model.exception.BusinessValidationException;
import io.github.oguzhancevik.stockmanagement.model.request.ProductRequest;
import io.github.oguzhancevik.stockmanagement.model.response.ProductDTO;
import io.github.oguzhancevik.stockmanagement.repository.ProductRepository;
import io.github.oguzhancevik.stockmanagement.repository.SubCategoryRepository;
import io.github.oguzhancevik.stockmanagement.service.ProductCommandService;
import io.github.oguzhancevik.stockmanagement.util.BaseMapper;
import io.github.oguzhancevik.stockmanagement.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static io.github.oguzhancevik.stockmanagement.model.exception.BusinessValidationRule.PRODUCT_NOT_FOUND;
import static io.github.oguzhancevik.stockmanagement.model.exception.BusinessValidationRule.SUB_CATEGORY_NOT_FOUND;

@Service
public class ProductCommandServiceImpl implements ProductCommandService {

    private final ProductRepository productRepository;
    private final SubCategoryRepository subCategoryRepository;

    @Autowired
    public ProductCommandServiceImpl(ProductRepository productRepository, SubCategoryRepository subCategoryRepository) {
        this.productRepository = productRepository;
        this.subCategoryRepository = subCategoryRepository;
    }

    @Override
    @Transactional
    public ProductDTO create(ProductRequest request) {
        var product = new Product(request.getName(), request.getPrice(), request.getStockAmount());
        var subCategory = subCategoryRepository.findById(request.getSubCategoryId())
                .orElseThrow(() -> new BusinessValidationException(SUB_CATEGORY_NOT_FOUND));
        product.setSubCategory(subCategory);
        productRepository.save(product);
        return BaseMapper.INSTANCE.toDTO(product);
    }

    @Override
    @Transactional
    @CacheEvict(value = Constants.CACHE.STOCK, key = "#productId")
    public ProductDTO update(Long productId, ProductRequest request) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new BusinessValidationException(PRODUCT_NOT_FOUND));
        SubCategory subCategory = subCategoryRepository.findById(request.getSubCategoryId())
                .orElseThrow(() -> new BusinessValidationException(SUB_CATEGORY_NOT_FOUND));
        product.setSubCategory(subCategory);
        BaseMapper.INSTANCE.fromRequest(request, product);
        return BaseMapper.INSTANCE.toDTO(product);
    }

    @Override
    @Transactional
    @CacheEvict(value = Constants.CACHE.STOCK, key = "#productId")
    public void deleteById(Long productId) {
        productRepository.deleteById(productId);
    }

}
