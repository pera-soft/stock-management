package io.github.oguzhancevik.stockmanagement.service.integration;

import io.github.oguzhancevik.stockmanagement.base.BaseUnitTest;
import io.github.oguzhancevik.stockmanagement.model.exception.BusinessValidationException;
import io.github.oguzhancevik.stockmanagement.model.request.CategoryRequest;
import io.github.oguzhancevik.stockmanagement.model.response.CategoryDTO;
import io.github.oguzhancevik.stockmanagement.service.CategoryCommandService;
import io.github.oguzhancevik.stockmanagement.service.CategoryQueryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;

import static org.junit.jupiter.api.Assertions.*;

class CategoryCommandServiceIntegrationTest extends BaseUnitTest {

    @Autowired
    private CategoryQueryService queryService;

    @Autowired
    private CategoryCommandService commandService;

    @Test
    public void create() {
        CategoryRequest request = dtoFactory.categoryRequest();
        CategoryDTO category = commandService.create(request);
        assertNotNull(category);
        assertEquals(request.getName(), category.getName());
    }

    @Test
    public void update() {
        CategoryRequest request = dtoFactory.categoryRequest();
        CategoryDTO category = commandService.update(1L, request);
        assertNotNull(category);
        assertEquals(request.getName(), category.getName());
    }

    @Test
    public void update_shouldThrowExceptionWhenCategoryNotFound() {
        assertThrows(BusinessValidationException.class, () -> commandService.update(0L, dtoFactory.categoryRequest()));
    }

    @Test
    public void deleteById() {
        Long categoryId = 1L;
        commandService.deleteById(categoryId);
        assertThrows(BusinessValidationException.class, () -> queryService.findById(categoryId));
    }

    @Test
    public void deleteById_shouldThrowExceptionWhenCategoryNotFound() {
        Long categoryId = 0L;
        assertThrows(EmptyResultDataAccessException.class, () -> commandService.deleteById(categoryId));
    }

}
