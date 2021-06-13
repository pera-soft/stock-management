package io.github.oguzhancevik.stockmanagement.service.integration;

import io.github.oguzhancevik.stockmanagement.base.BaseUnitTest;
import io.github.oguzhancevik.stockmanagement.model.exception.BusinessValidationException;
import io.github.oguzhancevik.stockmanagement.model.response.SubCategoryDTO;
import io.github.oguzhancevik.stockmanagement.service.SubCategoryQueryService;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class SubCategoryQueryServiceIntegrationTest extends BaseUnitTest {

    @Autowired
    private SubCategoryQueryService queryService;

    @Test
    public void findSubCategories() {
        List<SubCategoryDTO> subCategories = queryService.findSubCategories();
        assertNotNull(subCategories);
        MatcherAssert.assertThat(subCategories.size(), Matchers.equalTo(14));
        assertThat(subCategories).extracting("name")
                .containsAnyOf("Oil", "Coffee", "Chips")
                .doesNotContain("Computer");
    }

    @Test
    public void findById() {
        SubCategoryDTO subCategory = queryService.findById(15L);
        assertNotNull(subCategory);
        assertEquals("Chips", subCategory.getName());
    }

    @Test
    public void findById_shouldThrowExceptionWhenCategoryNotFound() {
        assertThrows(BusinessValidationException.class, () -> queryService.findById(0L));
    }

}
