package io.github.oguzhancevik.stockmanagement.service.mock;

import io.github.oguzhancevik.stockmanagement.base.BaseUnitTest;
import io.github.oguzhancevik.stockmanagement.model.entity.Category;
import io.github.oguzhancevik.stockmanagement.model.exception.BusinessValidationException;
import io.github.oguzhancevik.stockmanagement.model.request.CategoryRequest;
import io.github.oguzhancevik.stockmanagement.model.response.CategoryDTO;
import io.github.oguzhancevik.stockmanagement.repository.CategoryRepository;
import io.github.oguzhancevik.stockmanagement.service.impl.CategoryCommandServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class CategoryCommandServiceMockTest extends BaseUnitTest {

    private CategoryCommandServiceImpl commandService;
    private CategoryRepository categoryRepository;

    @BeforeEach
    public void setUp() {
        categoryRepository = mock(CategoryRepository.class);
        commandService = new CategoryCommandServiceImpl(categoryRepository);
    }

    @Test
    public void create() {
        Category category = entityFactory.category();
        category.setName("Vegetables");
        when(categoryRepository.save(any())).thenReturn(category);
        CategoryDTO result = commandService.create(dtoFactory.categoryRequest());
        assertNotNull(result);
        assertEquals(category.getName(), result.getName());
    }

    @Test
    public void update() {
        when(categoryRepository.findById(anyLong())).thenReturn(Optional.ofNullable(entityFactory.category()));
        CategoryRequest request = dtoFactory.categoryRequest();
        CategoryDTO category = commandService.update(anyLong(), request);
        assertNotNull(category);
        assertEquals(request.getName(), category.getName());
    }

    @Test
    public void update_shouldThrowExceptionWhenCategoryNotFound() {
        assertThrows(BusinessValidationException.class, () -> commandService.update(anyLong(), dtoFactory.categoryRequest()));
    }

    @Test
    public void deleteById() {
        commandService.deleteById(anyLong());
    }

    @Test
    public void deleteById_shouldThrowExceptionWhenCategoryNotFound() {
        Long categoryId = 0L;
        doThrow(new EmptyResultDataAccessException(categoryId.intValue())).when(categoryRepository).deleteById(categoryId);
        assertThrows(BusinessValidationException.class, () -> commandService.update(anyLong(), dtoFactory.categoryRequest()));
    }

}
