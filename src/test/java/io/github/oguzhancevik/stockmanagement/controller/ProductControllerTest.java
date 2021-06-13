package io.github.oguzhancevik.stockmanagement.controller;

import io.github.oguzhancevik.stockmanagement.base.BaseUnitTest;
import io.github.oguzhancevik.stockmanagement.model.request.ProductRequest;
import io.github.oguzhancevik.stockmanagement.service.ProductCommandService;
import io.github.oguzhancevik.stockmanagement.service.ProductQueryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;

import static io.github.oguzhancevik.stockmanagement.util.Constants.API.PRODUCT_MAPPING;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProductControllerTest extends BaseUnitTest {

    private MockMvc mockMvc;
    private ProductCommandService commandService;

    @BeforeEach
    public void setUp() {
        ProductQueryService queryService = mock(ProductQueryService.class);
        commandService = mock(ProductCommandService.class);
        ProductController controller = new ProductController(queryService, commandService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void list() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get(PRODUCT_MAPPING)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }

    @Test
    public void get() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get(PRODUCT_MAPPING + "/" + 50L)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }

    @Test
    public void searchByCategoryId() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get(PRODUCT_MAPPING + "/search/category/" + 1L)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }

    @Test
    public void searchBySubCategoryId() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get(PRODUCT_MAPPING + "/search/subcategory/" + 15L)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }

    @Test
    public void searchByProductName() throws Exception {
        String productName = "milk";
        mockMvc.perform(MockMvcRequestBuilders
                .get(PRODUCT_MAPPING + "/search/name/" + productName)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }

    @Test
    public void searchByPrice() throws Exception {
        BigDecimal minPrice = BigDecimal.valueOf(50);
        BigDecimal maxPrice = BigDecimal.valueOf(300);
        mockMvc.perform(MockMvcRequestBuilders
                .get(PRODUCT_MAPPING + "/search/price/?" + "min=" + minPrice + "&" + "max=" + maxPrice)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }

    @Test
    public void create() throws Exception {
        ProductRequest request = dtoFactory.productRequest();
        when(commandService.create(request)).thenReturn(dtoFactory.product());
        String json = toJson(request);
        mockMvc.perform(MockMvcRequestBuilders
                .post(PRODUCT_MAPPING)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(json))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn();
    }

    @Test
    public void update() throws Exception {
        ProductRequest request = dtoFactory.productRequest();
        when(commandService.create(request)).thenReturn(dtoFactory.product());
        String json = toJson(request);
        mockMvc.perform(MockMvcRequestBuilders
                .put(PRODUCT_MAPPING + "/" + 50L)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(json))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }

    @Test
    public void delete() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .delete(PRODUCT_MAPPING + "/" + 50L)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }

}
