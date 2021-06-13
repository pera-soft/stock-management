package io.github.oguzhancevik.stockmanagement.service.impl;

import io.github.oguzhancevik.stockmanagement.model.entity.Product;
import io.github.oguzhancevik.stockmanagement.model.exception.BusinessValidationException;
import io.github.oguzhancevik.stockmanagement.model.response.ProductDTO;
import io.github.oguzhancevik.stockmanagement.repository.ProductRepository;
import io.github.oguzhancevik.stockmanagement.service.ProductQueryService;
import io.github.oguzhancevik.stockmanagement.util.BaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static io.github.oguzhancevik.stockmanagement.model.exception.BusinessValidationRule.PRODUCT_NOT_FOUND;

@Service
public class ProductQueryServiceImpl implements ProductQueryService {

    private final ProductRepository repository;

    @Autowired
    public ProductQueryServiceImpl(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductDTO> findProducts() {
        List<Product> products = repository.findAll();
        List<ProductDTO> dtoList = new ArrayList<>(products.size());
        for (Product product : products) {
            dtoList.add(BaseMapper.INSTANCE.toDTO(product));
        }
        return dtoList;
    }

    @Override
    @Transactional(readOnly = true)
    public ProductDTO findById(Long productId) {
        Product product = repository.findById(productId)
                .orElseThrow(() -> new BusinessValidationException(PRODUCT_NOT_FOUND));
        return BaseMapper.INSTANCE.toDTO(product);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductDTO> findProductsByCategoryId(Long categoryId) {
        List<Product> products = repository.findBySubCategoryCategoryId(categoryId)
                .orElseThrow(() -> new BusinessValidationException(PRODUCT_NOT_FOUND));
        List<ProductDTO> dtoList = new ArrayList<>(products.size());
        for (Product product : products) {
            dtoList.add(BaseMapper.INSTANCE.toDTO(product));
        }
        return dtoList;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductDTO> findProductsBySubCategoryId(Long subCategoryId) {
        List<Product> products = repository.findBySubCategoryId(subCategoryId)
                .orElseThrow(() -> new BusinessValidationException(PRODUCT_NOT_FOUND));
        List<ProductDTO> dtoList = new ArrayList<>(products.size());
        for (Product product : products) {
            dtoList.add(BaseMapper.INSTANCE.toDTO(product));
        }
        return dtoList;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductDTO> findProductsByName(String name) {
        List<Product> products = repository.findByNameContainingIgnoreCase(name)
                .orElseThrow(() -> new BusinessValidationException(PRODUCT_NOT_FOUND));
        List<ProductDTO> dtoList = new ArrayList<>(products.size());
        for (Product product : products) {
            dtoList.add(BaseMapper.INSTANCE.toDTO(product));
        }
        return dtoList;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductDTO> findProductsByPrice(BigDecimal minPrice, BigDecimal maxPrice) {
        List<Product> products = repository.findByPriceBetween(minPrice, maxPrice)
                .orElseThrow(() -> new BusinessValidationException(PRODUCT_NOT_FOUND));
        List<ProductDTO> dtoList = new ArrayList<>(products.size());
        for (Product product : products) {
            dtoList.add(BaseMapper.INSTANCE.toDTO(product));
        }
        return dtoList;
    }

}
