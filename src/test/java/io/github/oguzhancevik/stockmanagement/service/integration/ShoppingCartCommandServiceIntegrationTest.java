package io.github.oguzhancevik.stockmanagement.service.integration;

import io.github.oguzhancevik.stockmanagement.base.BaseUnitTest;
import io.github.oguzhancevik.stockmanagement.model.exception.BusinessValidationException;
import io.github.oguzhancevik.stockmanagement.model.request.ShoppingRequest;
import io.github.oguzhancevik.stockmanagement.service.ShoppingCartCommandService;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.assertThrows;

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class ShoppingCartCommandServiceIntegrationTest extends BaseUnitTest {

    @Autowired
    private ShoppingCartCommandService commandService;

    @Test
    @Order(1)
    public void shopping() {
        ShoppingRequest request = dtoFactory.shoppingRequest();
        request.setProductId(18L);
        commandService.shopping(request);
    }

    @Test
    @Order(2)
    public void shopping_shouldThrowExceptionWhenOutOfStock() {
        ShoppingRequest request = dtoFactory.shoppingRequest();
        request.setProductId(18L);
        assertThrows(BusinessValidationException.class, () -> commandService.shopping(request));
    }

}
