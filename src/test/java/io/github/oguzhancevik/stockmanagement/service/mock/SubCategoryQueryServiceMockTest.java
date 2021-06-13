package io.github.oguzhancevik.stockmanagement.service.mock;

import io.github.oguzhancevik.stockmanagement.base.BaseUnitTest;
import io.github.oguzhancevik.stockmanagement.model.exception.BusinessValidationException;
import io.github.oguzhancevik.stockmanagement.model.response.SubCategoryDTO;
import io.github.oguzhancevik.stockmanagement.repository.SubCategoryRepository;
import io.github.oguzhancevik.stockmanagement.service.impl.SubCategoryQueryServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SubCategoryQueryServiceMockTest extends BaseUnitTest {

    private SubCategoryQueryServiceImpl queryService;
    private SubCategoryRepository repository;

    @BeforeEach
    public void setUp() {
        repository = mock(SubCategoryRepository.class);
        queryService = new SubCategoryQueryServiceImpl(repository);
    }

    @Test
    public void findSubCategories() {
        when(repository.findAll()).thenReturn(entityFactory.subCategories());
        List<SubCategoryDTO> subCategories = queryService.findSubCategories();
        assertNotNull(subCategories);
        assertThat(subCategories).extracting("name").containsAnyOf("Chips").doesNotContain("Tea");
    }

    @Test
    public void findById() {
        when(repository.findById(anyLong())).thenReturn(Optional.ofNullable(entityFactory.subCategory()));
        SubCategoryDTO subCategory = queryService.findById(anyLong());
        assertNotNull(subCategory);
        assertEquals("Chips", subCategory.getName());
    }

    @Test
    public void findById_shouldThrowExceptionWhenCategoryNotFound() {
        assertThrows(BusinessValidationException.class, () -> queryService.findById(anyLong()));
    }

}
