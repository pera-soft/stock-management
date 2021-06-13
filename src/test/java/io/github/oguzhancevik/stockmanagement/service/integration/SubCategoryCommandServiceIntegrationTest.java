package io.github.oguzhancevik.stockmanagement.service.integration;

import io.github.oguzhancevik.stockmanagement.base.BaseUnitTest;
import io.github.oguzhancevik.stockmanagement.model.exception.BusinessValidationException;
import io.github.oguzhancevik.stockmanagement.model.request.SubCategoryRequest;
import io.github.oguzhancevik.stockmanagement.model.response.SubCategoryDTO;
import io.github.oguzhancevik.stockmanagement.service.SubCategoryCommandService;
import io.github.oguzhancevik.stockmanagement.service.SubCategoryQueryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;

import static org.junit.jupiter.api.Assertions.*;

class SubCategoryCommandServiceIntegrationTest extends BaseUnitTest {

    @Autowired
    private SubCategoryQueryService queryService;

    @Autowired
    private SubCategoryCommandService commandService;

    @Test
    public void create() {
        SubCategoryRequest request = dtoFactory.subCategoryRequest();
        SubCategoryDTO subCategory = commandService.create(request);
        assertNotNull(subCategory);
        assertEquals(request.getName(), subCategory.getName());
    }

    @Test
    public void create_shouldThrowExceptionWhenCategoryNotFound() {
        SubCategoryRequest request = dtoFactory.subCategoryRequest();
        request.setCategoryId(999L);
        assertThrows(BusinessValidationException.class, () -> commandService.create(request));
    }

    @Test
    public void update() {
        SubCategoryRequest request = dtoFactory.subCategoryRequest();
        SubCategoryDTO subCategory = commandService.update(15L, request);
        assertNotNull(subCategory);
        assertEquals(request.getName(), subCategory.getName());
    }

    @Test
    public void update_shouldThrowExceptionWhenSubCategoryNotFound() {
        assertThrows(BusinessValidationException.class, () -> commandService.update(0L, dtoFactory.subCategoryRequest()));
    }

    @Test
    public void update_shouldThrowExceptionWhenCategoryNotFound() {
        SubCategoryRequest request = dtoFactory.subCategoryRequest();
        request.setCategoryId(999L);
        assertThrows(BusinessValidationException.class, () -> commandService.update(15L, request));
    }

    @Test
    public void deleteById() {
        Long subCategoryId = 15L;
        commandService.deleteById(subCategoryId);
        assertThrows(BusinessValidationException.class, () -> queryService.findById(subCategoryId));
    }

    @Test
    public void deleteById_shouldThrowExceptionWhenSubCategoryNotFound() {
        Long subCategoryId = 999L;
        assertThrows(EmptyResultDataAccessException.class, () -> commandService.deleteById(subCategoryId));
    }

}
