package io.github.oguzhancevik.stockmanagement.service.mock;

import io.github.oguzhancevik.stockmanagement.base.BaseUnitTest;
import io.github.oguzhancevik.stockmanagement.model.exception.BusinessValidationException;
import io.github.oguzhancevik.stockmanagement.model.response.CategoryDTO;
import io.github.oguzhancevik.stockmanagement.repository.CategoryRepository;
import io.github.oguzhancevik.stockmanagement.service.impl.CategoryQueryServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CategoryQueryServiceMockTest extends BaseUnitTest {

    private CategoryQueryServiceImpl queryService;
    private CategoryRepository repository;

    @BeforeEach
    public void setUp() {
        repository = mock(CategoryRepository.class);
        queryService = new CategoryQueryServiceImpl(repository);
    }

    @Test
    public void findCategories() {
        when(repository.findAll()).thenReturn(entityFactory.categories());
        List<CategoryDTO> categories = queryService.findCategories();
        assertNotNull(categories);
        assertThat(categories).extracting("name").containsAnyOf("Snacks").doesNotContain("Vegetables");
    }

    @Test
    public void findById() {
        when(repository.findById(anyLong())).thenReturn(Optional.ofNullable(entityFactory.category()));
        CategoryDTO category = queryService.findById(anyLong());
        assertNotNull(category);
        assertEquals("Snacks", category.getName());
    }

    @Test
    public void findById_shouldThrowExceptionWhenCategoryNotFound() {
        assertThrows(BusinessValidationException.class, () -> queryService.findById(anyLong()));
    }

}
