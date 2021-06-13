package io.github.oguzhancevik.stockmanagement.service.mock;

import io.github.oguzhancevik.stockmanagement.base.BaseUnitTest;
import io.github.oguzhancevik.stockmanagement.model.entity.SubCategory;
import io.github.oguzhancevik.stockmanagement.model.exception.BusinessValidationException;
import io.github.oguzhancevik.stockmanagement.model.request.SubCategoryRequest;
import io.github.oguzhancevik.stockmanagement.model.response.SubCategoryDTO;
import io.github.oguzhancevik.stockmanagement.repository.CategoryRepository;
import io.github.oguzhancevik.stockmanagement.repository.SubCategoryRepository;
import io.github.oguzhancevik.stockmanagement.service.impl.SubCategoryCommandServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class SubCategoryCommandServiceMockTest extends BaseUnitTest {

    private SubCategoryCommandServiceImpl commandService;
    private SubCategoryRepository subCategoryRepository;
    private CategoryRepository categoryRepository;

    @BeforeEach
    public void setUp() {
        subCategoryRepository = mock(SubCategoryRepository.class);
        categoryRepository = mock(CategoryRepository.class);
        commandService = new SubCategoryCommandServiceImpl(subCategoryRepository, categoryRepository);
    }

    @Test
    public void create() {
        SubCategoryRequest request = dtoFactory.subCategoryRequest();
        SubCategory subCategory = entityFactory.subCategory();
        subCategory.setName(request.getName());

        when(categoryRepository.findById(request.getCategoryId())).thenReturn(Optional.ofNullable(entityFactory.category()));
        when(subCategoryRepository.save(any())).thenReturn(subCategory);

        SubCategoryDTO result = commandService.create(request);
        assertNotNull(result);
        assertEquals(subCategory.getName(), result.getName());
    }

    @Test
    public void create_shouldThrowExceptionWhenCategoryNotFound() {
        SubCategoryRequest request = dtoFactory.subCategoryRequest();
        assertThrows(BusinessValidationException.class, () -> commandService.create(request));
    }

    @Test
    public void update() {
        SubCategoryRequest request = dtoFactory.subCategoryRequest();
        when(subCategoryRepository.findById(anyLong())).thenReturn(Optional.ofNullable(entityFactory.subCategory()));
        when(categoryRepository.findById(request.getCategoryId())).thenReturn(Optional.ofNullable(entityFactory.category()));
        SubCategoryDTO subCategory = commandService.update(anyLong(), request);
        assertNotNull(subCategory);
        assertEquals(request.getName(), subCategory.getName());
    }

    @Test
    public void update_shouldThrowExceptionWhenSubCategoryNotFound() {
        assertThrows(BusinessValidationException.class, () -> commandService.update(anyLong(), dtoFactory.subCategoryRequest()));
    }

    @Test
    public void update_shouldThrowExceptionWhenCategoryNotFound() {
        when(subCategoryRepository.findById(anyLong())).thenReturn(Optional.ofNullable(entityFactory.subCategory()));
        assertThrows(BusinessValidationException.class, () -> commandService.update(anyLong(), dtoFactory.subCategoryRequest()));
    }

    @Test
    public void deleteById() {
        commandService.deleteById(anyLong());
    }

    @Test
    public void deleteById_shouldThrowExceptionWhenSubCategoryNotFound() {
        Long subCategoryId = 0L;
        doThrow(new EmptyResultDataAccessException(subCategoryId.intValue())).when(subCategoryRepository).deleteById(subCategoryId);
        assertThrows(EmptyResultDataAccessException.class, () -> commandService.deleteById(subCategoryId));
    }

}
