package io.github.oguzhancevik.stockmanagement.service.integration;

import io.github.oguzhancevik.stockmanagement.base.BaseUnitTest;
import io.github.oguzhancevik.stockmanagement.model.exception.BusinessValidationException;
import io.github.oguzhancevik.stockmanagement.model.response.ProductDTO;
import io.github.oguzhancevik.stockmanagement.service.ProductQueryService;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ProductQueryServiceIntegrationTest extends BaseUnitTest {

    @Autowired
    private ProductQueryService queryService;

    @Test
    public void findProducts() {
        List<ProductDTO> products = queryService.findProducts();
        assertNotNull(products);
        MatcherAssert.assertThat(products.size(), Matchers.equalTo(50));
        assertThat(products).extracting("name")
                .containsAnyOf("Filiz Spaghetti", "Coca-Cola", "Snickers")
                .doesNotContain("Computer");
    }

    @Test
    public void findById() {
        ProductDTO product = queryService.findById(57L);
        assertNotNull(product);
        assertEquals("Snickers", product.getName());
    }

    @Test
    public void findById_shouldThrowExceptionWhenProductNotFound() {
        assertThrows(BusinessValidationException.class, () -> queryService.findById(999L));
    }

    @Test
    public void findProductsByCategoryId() {
        List<ProductDTO> products = queryService.findProductsByCategoryId(1L);
        assertNotNull(products);
        MatcherAssert.assertThat(products.size(), Matchers.equalTo(10));
        assertThat(products).extracting("name")
                .containsAnyOf("Filiz Spaghetti", "Barilla Spaghetti")
                .doesNotContain("Snickers");
    }

    @Test
    public void findProductsByCategoryId_shouldThrowExceptionWhenProductNotFound() {
        assertThrows(BusinessValidationException.class, () -> queryService.findProductsByCategoryId(999L));
    }

    @Test
    public void findProductsBySubCategoryId() {
        List<ProductDTO> products = queryService.findProductsBySubCategoryId(15L);
        assertNotNull(products);
        MatcherAssert.assertThat(products.size(), Matchers.equalTo(4));
        assertThat(products).extracting("name")
                .containsAnyOf("Lays Classic", "Ruffles Originals")
                .doesNotContain("Barilla Spaghetti");
    }

    @Test
    public void findProductsBySubCategoryId_shouldThrowExceptionWhenProductNotFound() {
        assertThrows(BusinessValidationException.class, () -> queryService.findProductsBySubCategoryId(999L));
    }

    @Test
    public void findProductsByName() {
        List<ProductDTO> products = queryService.findProductsByName("milk");
        assertNotNull(products);
        MatcherAssert.assertThat(products.size(), Matchers.equalTo(4));
        assertThat(products).extracting("name")
                .containsAnyOf("Danone Cocoa Milk", "Sek 3% Milk")
                .doesNotContain("Lays Classic");
    }

    @Test
    public void findProductsByName_shouldThrowExceptionWhenProductNotFound() {
        assertThrows(BusinessValidationException.class, () -> queryService.findProductsByName("computer"));
    }

    @Test
    public void findProductsByPrice() {
        List<ProductDTO> products = queryService.findProductsByPrice(BigDecimal.valueOf(50), BigDecimal.valueOf(300));
        assertNotNull(products);
        MatcherAssert.assertThat(products.size(), Matchers.equalTo(2));
        assertThat(products).extracting("name")
                .containsAnyOf("Komili Natural Olive Oil", "Yudum Natural Virgin Olive Oil")
                .doesNotContain("Sek 3% Milk");
    }

    @Test
    public void findProductsByPrice_shouldThrowExceptionWhenProductNotFound() {
        assertThrows(BusinessValidationException.class,
                () -> queryService.findProductsByPrice(BigDecimal.valueOf(1000), BigDecimal.valueOf(1500)));
    }

}
