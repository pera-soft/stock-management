package io.github.oguzhancevik.stockmanagement.service.mock;

import io.github.oguzhancevik.stockmanagement.base.BaseUnitTest;
import io.github.oguzhancevik.stockmanagement.model.entity.Category;
import io.github.oguzhancevik.stockmanagement.model.exception.BusinessValidationException;
import io.github.oguzhancevik.stockmanagement.model.exception.BusinessValidationRule;
import io.github.oguzhancevik.stockmanagement.model.request.CategoryRequest;
import io.github.oguzhancevik.stockmanagement.model.response.CategoryDTO;
import io.github.oguzhancevik.stockmanagement.repository.CategoryRepository;
import io.github.oguzhancevik.stockmanagement.service.impl.CategoryQueryServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class CategoryQueryServiceMockTest extends BaseUnitTest {

    private CategoryQueryServiceImpl categoryQueryService;
    private CategoryRepository categoryRepository;

    @BeforeEach
    public void setUp() {
        categoryRepository = mock(CategoryRepository.class);
        categoryQueryService = new CategoryQueryServiceImpl(categoryRepository);
    }

    @Test
    public void findCategories() {
        when(categoryRepository.findAll()).thenReturn(entityFactory.categories());
        List<CategoryDTO> categories = categoryQueryService.findCategories();
        assertNotNull(categories);
        assertThat(categories).extracting("name").containsAnyOf("Snacks").doesNotContain("Vegetables");
    }

    @Test
    public void findById() {
        when(categoryRepository.findById(anyLong())).thenReturn(Optional.ofNullable(entityFactory.category()));
        CategoryDTO category = categoryQueryService.findById(anyLong());
        assertNotNull(category);
        assertEquals("Snacks", category.getName());
    }

    @Test
    public void findById_shouldThrowExceptionWhenCategoryNotFound() {
        assertThrows(BusinessValidationException.class, () -> categoryQueryService.findById(anyLong()));
    }

    @Test
    public void create() {
        Category category = entityFactory.category();
        category.setName("Vegetables");
        when(categoryRepository.save(any())).thenReturn(category);
        CategoryDTO result = categoryQueryService.create(dtoFactory.categoryRequest());
        assertNotNull(result);
        assertEquals(category.getName(), result.getName());
    }

    @Test
    public void update() {
        when(categoryRepository.findById(anyLong())).thenReturn(Optional.ofNullable(entityFactory.category()));
        CategoryRequest request = dtoFactory.categoryRequest();
        CategoryDTO category = categoryQueryService.update(anyLong(), request);
        assertNotNull(category);
        assertEquals(request.getName(), category.getName());
    }

    @Test
    public void update_shouldThrowExceptionWhenCategoryNotFound() {
        assertThrows(BusinessValidationException.class, () -> categoryQueryService.update(anyLong(), dtoFactory.categoryRequest()));
    }

    @Test
    public void deleteById() {
        categoryQueryService.deleteById(anyLong());
    }

    @Test
    public void deleteById_shouldThrowExceptionWhenCategoryNotFound() {
        Long categoryId = 0L;
        doThrow(new EmptyResultDataAccessException(categoryId.intValue())).when(categoryRepository).deleteById(categoryId);
        assertThrows(BusinessValidationException.class, () -> categoryQueryService.update(anyLong(), dtoFactory.categoryRequest()));
    }

}
