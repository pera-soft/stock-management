package io.github.oguzhancevik.stockmanagement.controller;

import io.github.oguzhancevik.stockmanagement.base.BaseUnitTest;
import io.github.oguzhancevik.stockmanagement.model.request.ShoppingRequest;
import io.github.oguzhancevik.stockmanagement.service.ShoppingCartCommandService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static io.github.oguzhancevik.stockmanagement.util.Constants.API.SHOPPING_CART_MAPPING;
import static org.mockito.Mockito.mock;

public class ShoppingCartControllerTest extends BaseUnitTest {

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        var commandService = mock(ShoppingCartCommandService.class);
        var controller = new ShoppingCartController(commandService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void shopping() throws Exception {
        ShoppingRequest request = dtoFactory.shoppingRequest();
        String json = toJson(request);
        mockMvc.perform(MockMvcRequestBuilders
                .post(SHOPPING_CART_MAPPING)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(json))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }

}
