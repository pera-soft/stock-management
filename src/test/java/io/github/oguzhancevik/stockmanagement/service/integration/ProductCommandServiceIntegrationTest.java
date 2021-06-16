package io.github.oguzhancevik.stockmanagement.service.integration;

import io.github.oguzhancevik.stockmanagement.base.BaseUnitTest;
import io.github.oguzhancevik.stockmanagement.model.exception.BusinessValidationException;
import io.github.oguzhancevik.stockmanagement.model.request.ProductRequest;
import io.github.oguzhancevik.stockmanagement.model.response.ProductDTO;
import io.github.oguzhancevik.stockmanagement.service.ProductCommandService;
import io.github.oguzhancevik.stockmanagement.service.ProductQueryService;
import io.github.oguzhancevik.stockmanagement.util.Constants;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles(Constants.PROFILE.LOCAL)
public class ProductCommandServiceIntegrationTest extends BaseUnitTest {

    @Autowired
    private ProductQueryService queryService;

    @Autowired
    private ProductCommandService commandService;

    @Test
    public void create() {
        ProductRequest request = dtoFactory.productRequest();
        ProductDTO product = commandService.create(request);
        assertNotNull(product);
        assertEquals(request.getName(), product.getName());
    }

    @Test
    public void create_shouldThrowExceptionWhenSubCategoryNotFound() {
        ProductRequest request = dtoFactory.productRequest();
        request.setSubCategoryId(999L);
        assertThrows(BusinessValidationException.class, () -> commandService.create(request));
    }

    @Test
    public void update() {
        ProductRequest request = dtoFactory.productRequest();
        ProductDTO product = commandService.update(50L, request);
        assertNotNull(product);
        assertEquals(request.getName(), product.getName());
    }

    @Test
    public void update_shouldThrowExceptionWhenProductNotFound() {
        assertThrows(BusinessValidationException.class, () -> commandService.update(999L, dtoFactory.productRequest()));
    }

    @Test
    public void update_shouldThrowExceptionWhenSubCategoryNotFound() {
        ProductRequest request = dtoFactory.productRequest();
        request.setSubCategoryId(999L);
        assertThrows(BusinessValidationException.class, () -> commandService.update(50L, request));
    }

    @Test
    public void deleteById() {
        Long productId = 50L;
        commandService.deleteById(productId);
        assertThrows(BusinessValidationException.class, () -> queryService.findById(productId));
    }

    @Test
    public void deleteById_shouldThrowExceptionWhenProductNotFound() {
        Long productId = 999L;
        assertThrows(EmptyResultDataAccessException.class, () -> commandService.deleteById(productId));
    }

}
