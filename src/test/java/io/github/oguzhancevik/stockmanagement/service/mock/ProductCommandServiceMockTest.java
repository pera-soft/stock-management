package io.github.oguzhancevik.stockmanagement.service.mock;

import io.github.oguzhancevik.stockmanagement.base.BaseUnitTest;
import io.github.oguzhancevik.stockmanagement.model.entity.Product;
import io.github.oguzhancevik.stockmanagement.model.exception.BusinessValidationException;
import io.github.oguzhancevik.stockmanagement.model.request.ProductRequest;
import io.github.oguzhancevik.stockmanagement.model.response.ProductDTO;
import io.github.oguzhancevik.stockmanagement.repository.ProductRepository;
import io.github.oguzhancevik.stockmanagement.repository.SubCategoryRepository;
import io.github.oguzhancevik.stockmanagement.service.impl.ProductCommandServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class ProductCommandServiceMockTest extends BaseUnitTest {

    private ProductCommandServiceImpl commandService;
    private ProductRepository productRepository;
    private SubCategoryRepository subCategoryRepository;

    @BeforeEach
    public void setUp() {
        productRepository = mock(ProductRepository.class);
        subCategoryRepository = mock(SubCategoryRepository.class);
        commandService = new ProductCommandServiceImpl(productRepository, subCategoryRepository);
    }

    @Test
    public void create() {
        ProductRequest request = dtoFactory.productRequest();
        Product product = entityFactory.product();
        product.setName(request.getName());

        when(subCategoryRepository.findById(request.getSubCategoryId())).thenReturn(Optional.ofNullable(entityFactory.subCategory()));
        when(productRepository.save(any())).thenReturn(product);

        ProductDTO result = commandService.create(request);
        assertNotNull(result);
        assertEquals(product.getName(), result.getName());
    }

    @Test
    public void create_shouldThrowExceptionWhenSubCategoryNotFound() {
        ProductRequest request = dtoFactory.productRequest();
        assertThrows(BusinessValidationException.class, () -> commandService.create(request));
    }

    @Test
    public void update() {
        ProductRequest request = dtoFactory.productRequest();
        when(productRepository.findById(anyLong())).thenReturn(Optional.ofNullable(entityFactory.product()));
        when(subCategoryRepository.findById(request.getSubCategoryId())).thenReturn(Optional.ofNullable(entityFactory.subCategory()));
        ProductDTO product = commandService.update(anyLong(), request);
        assertNotNull(product);
        assertEquals(request.getName(), product.getName());
    }

    @Test
    public void update_shouldThrowExceptionWhenProductNotFound() {
        assertThrows(BusinessValidationException.class, () -> commandService.update(anyLong(), dtoFactory.productRequest()));
    }

    @Test
    public void update_shouldThrowExceptionWhenSubCategoryNotFound() {
        when(productRepository.findById(anyLong())).thenReturn(Optional.ofNullable(entityFactory.product()));
        assertThrows(BusinessValidationException.class, () -> commandService.update(anyLong(), dtoFactory.productRequest()));
    }

    @Test
    public void deleteById() {
        commandService.deleteById(anyLong());
    }

    @Test
    public void deleteById_shouldThrowExceptionWhenProductNotFound() {
        Long productId = 0L;
        doThrow(new EmptyResultDataAccessException(productId.intValue())).when(productRepository).deleteById(productId);
        assertThrows(EmptyResultDataAccessException.class, () -> commandService.deleteById(productId));
    }

}
