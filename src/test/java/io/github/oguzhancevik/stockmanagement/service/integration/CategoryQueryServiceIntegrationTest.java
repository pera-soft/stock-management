package io.github.oguzhancevik.stockmanagement.service.integration;

import io.github.oguzhancevik.stockmanagement.base.BaseUnitTest;
import io.github.oguzhancevik.stockmanagement.model.exception.BusinessValidationException;
import io.github.oguzhancevik.stockmanagement.model.request.CategoryRequest;
import io.github.oguzhancevik.stockmanagement.model.response.CategoryDTO;
import io.github.oguzhancevik.stockmanagement.service.CategoryQueryService;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CategoryQueryServiceIntegrationTest extends BaseUnitTest {

    @Autowired
    private CategoryQueryService categoryQueryService;

    @Test
    public void findCategories() {
        List<CategoryDTO> categories = categoryQueryService.findCategories();
        assertNotNull(categories);
        MatcherAssert.assertThat(categories.size(), Matchers.equalTo(3));
        assertThat(categories).extracting("name")
                .contains("Food", "Beverages", "Snacks")
                .doesNotContain("Vegetables");
    }

    @Test
    public void findById() {
        CategoryDTO category = categoryQueryService.findById(1L);
        assertNotNull(category);
        assertEquals("Food", category.getName());
    }

    @Test
    public void findById_shouldThrowExceptionWhenCategoryNotFound() {
        assertThrows(BusinessValidationException.class, () -> categoryQueryService.findById(0L));
    }

    @Test
    public void create() {
        CategoryRequest request = dtoFactory.categoryRequest();
        CategoryDTO category = categoryQueryService.create(request);
        assertNotNull(category);
        assertEquals(request.getName(), category.getName());
    }

    @Test
    public void update() {
        CategoryRequest request = dtoFactory.categoryRequest();
        CategoryDTO category = categoryQueryService.update(1L, request);
        assertNotNull(category);
        assertEquals(request.getName(), category.getName());
    }

    @Test
    public void update_shouldThrowExceptionWhenCategoryNotFound() {
        assertThrows(BusinessValidationException.class, () -> categoryQueryService.update(0L, dtoFactory.categoryRequest()));
    }

    @Test
    public void deleteById() {
        Long categoryId = 1L;
        categoryQueryService.deleteById(categoryId);
        assertThrows(BusinessValidationException.class, () -> categoryQueryService.findById(categoryId));
    }

    @Test
    public void deleteById_shouldThrowExceptionWhenCategoryNotFound() {
        Long categoryId = 0L;
        assertThrows(EmptyResultDataAccessException.class, () -> categoryQueryService.deleteById(categoryId));
    }

}
