package io.github.oguzhancevik.stockmanagement.service.integration;

import io.github.oguzhancevik.stockmanagement.base.BaseUnitTest;
import io.github.oguzhancevik.stockmanagement.model.exception.BusinessValidationException;
import io.github.oguzhancevik.stockmanagement.model.response.CategoryDTO;
import io.github.oguzhancevik.stockmanagement.service.CategoryQueryService;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CategoryQueryServiceIntegrationTest extends BaseUnitTest {

    @Autowired
    private CategoryQueryService queryService;

    @Test
    public void findCategories() {
        List<CategoryDTO> categories = queryService.findCategories();
        assertNotNull(categories);
        MatcherAssert.assertThat(categories.size(), Matchers.equalTo(3));
        assertThat(categories).extracting("name")
                .contains("Food", "Beverages", "Snacks")
                .doesNotContain("Vegetables");
    }

    @Test
    public void findById() {
        CategoryDTO category = queryService.findById(1L);
        assertNotNull(category);
        assertEquals("Food", category.getName());
    }

    @Test
    public void findById_shouldThrowExceptionWhenCategoryNotFound() {
        assertThrows(BusinessValidationException.class, () -> queryService.findById(0L));
    }

}
