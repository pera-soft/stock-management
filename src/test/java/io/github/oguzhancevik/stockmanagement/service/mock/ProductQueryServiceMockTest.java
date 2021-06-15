package io.github.oguzhancevik.stockmanagement.service.mock;

import io.github.oguzhancevik.stockmanagement.base.BaseUnitTest;
import io.github.oguzhancevik.stockmanagement.model.exception.BusinessValidationException;
import io.github.oguzhancevik.stockmanagement.model.response.ProductDTO;
import io.github.oguzhancevik.stockmanagement.repository.ProductRepository;
import io.github.oguzhancevik.stockmanagement.service.impl.ProductQueryServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProductQueryServiceMockTest extends BaseUnitTest {

    private ProductQueryServiceImpl queryService;
    private ProductRepository repository;

    @BeforeEach
    public void setUp() {
        repository = mock(ProductRepository.class);
        queryService = new ProductQueryServiceImpl(repository);
    }

    @Test
    public void findProducts() {
        when(repository.findAll()).thenReturn(entityFactory.products());
        List<ProductDTO> products = queryService.findProducts();
        assertNotNull(products);
        assertThat(products).extracting("name").containsAnyOf("Pringles Original").doesNotContain("Doritos");
    }

    @Test
    public void findById() {
        when(repository.findById(anyLong())).thenReturn(Optional.ofNullable(entityFactory.product()));
        ProductDTO product = queryService.findById(anyLong());
        assertNotNull(product);
        assertEquals("Pringles Original", product.getName());
    }

    @Test
    public void findById_shouldThrowExceptionWhenProductNotFound() {
        assertThrows(BusinessValidationException.class, () -> queryService.findById(anyLong()));
    }

    @Test
    public void findProductsByCategoryId() {
        when(repository.findBySubCategoryCategoryId(anyLong())).thenReturn(entityFactory.products());
        List<ProductDTO> products = queryService.findProductsByCategoryId(anyLong());
        assertNotNull(products);
        assertThat(products).extracting("name").containsAnyOf("Pringles Original").doesNotContain("Doritos");
    }

    @Test
    public void findProductsByCategoryId_shouldThrowExceptionWhenProductNotFound() {
        assertThrows(BusinessValidationException.class, () -> queryService.findProductsByCategoryId(anyLong()));
    }

    @Test
    public void findProductsBySubCategoryId() {
        when(repository.findBySubCategoryId(anyLong())).thenReturn(entityFactory.products());
        List<ProductDTO> products = queryService.findProductsBySubCategoryId(anyLong());
        assertNotNull(products);
        assertThat(products).extracting("name").containsAnyOf("Pringles Original").doesNotContain("Doritos");
    }

    @Test
    public void findProductsBySubCategoryId_shouldThrowExceptionWhenProductNotFound() {
        assertThrows(BusinessValidationException.class, () -> queryService.findProductsBySubCategoryId(anyLong()));
    }

    @Test
    public void findProductsByName() {
        when(repository.findByNameContainingIgnoreCase(anyString())).thenReturn(entityFactory.products());
        List<ProductDTO> products = queryService.findProductsByName(anyString());
        assertNotNull(products);
        assertThat(products).extracting("name").containsAnyOf("Pringles Original").doesNotContain("Doritos");
    }

    @Test
    public void findProductsByName_shouldThrowExceptionWhenProductNotFound() {
        assertThrows(BusinessValidationException.class, () -> queryService.findProductsByName(anyString()));
    }

    @Test
    public void findProductsByPrice() {
        when(repository.findByPriceBetween(any(), any())).thenReturn(entityFactory.products());
        List<ProductDTO> products = queryService.findProductsByPrice(any(), any());
        assertNotNull(products);
        assertThat(products).extracting("name").containsAnyOf("Pringles Original").doesNotContain("Doritos");
    }

    @Test
    public void findProductsByPrice_shouldThrowExceptionWhenProductNotFound() {
        assertThrows(BusinessValidationException.class, () -> queryService.findProductsByPrice(any(), any()));
    }

}
