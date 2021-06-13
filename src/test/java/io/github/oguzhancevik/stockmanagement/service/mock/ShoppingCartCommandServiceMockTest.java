package io.github.oguzhancevik.stockmanagement.service.mock;

import io.github.oguzhancevik.stockmanagement.base.BaseUnitTest;
import io.github.oguzhancevik.stockmanagement.model.exception.BusinessValidationException;
import io.github.oguzhancevik.stockmanagement.model.request.ShoppingRequest;
import io.github.oguzhancevik.stockmanagement.repository.ProductRepository;
import io.github.oguzhancevik.stockmanagement.service.impl.ShoppingCartCommandServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ShoppingCartCommandServiceMockTest extends BaseUnitTest {

    private ShoppingCartCommandServiceImpl commandService;
    private ProductRepository productRepository;

    @BeforeEach
    public void setUp() {
        productRepository = mock(ProductRepository.class);
        commandService = new ShoppingCartCommandServiceImpl(productRepository);
    }

    @Test
    public void shopping() {
        ShoppingRequest shoppingRequest = dtoFactory.shoppingRequest();
        when(productRepository.findProductById(shoppingRequest.getProductId())).thenReturn(Optional.of(entityFactory.product()));
        commandService.shopping(shoppingRequest);
    }

    @Test
    public void shopping_shouldThrowExceptionWhenOutOfStock() {
        ShoppingRequest shoppingRequest = dtoFactory.shoppingRequest();
        assertThrows(BusinessValidationException.class, () -> commandService.shopping(shoppingRequest));
    }

}
