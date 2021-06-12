package io.github.oguzhancevik.stockmanagement.controller;

import io.github.oguzhancevik.stockmanagement.base.BaseUnitTest;
import io.github.oguzhancevik.stockmanagement.model.request.CategoryRequest;
import io.github.oguzhancevik.stockmanagement.service.CategoryQueryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static io.github.oguzhancevik.stockmanagement.util.Constants.API.CATEGORY_MAPPING;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CategoryControllerTest extends BaseUnitTest {

    private MockMvc mockMvc;
    private CategoryQueryService categoryQueryService;

    @BeforeEach
    public void setUp() {
        categoryQueryService = mock(CategoryQueryService.class);
        CategoryController controller = new CategoryController(categoryQueryService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void list() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get(CATEGORY_MAPPING)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }

    @Test
    public void get() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get(CATEGORY_MAPPING + "/" + 1L)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }

    @Test
    public void create() throws Exception {
        CategoryRequest request = dtoFactory.categoryRequest();
        when(categoryQueryService.create(request)).thenReturn(dtoFactory.category());
        String json = toJson(request);
        mockMvc.perform(MockMvcRequestBuilders
                .post(CATEGORY_MAPPING)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(json))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn();
    }

    @Test
    public void update() throws Exception {
        CategoryRequest request = dtoFactory.categoryRequest();
        when(categoryQueryService.create(request)).thenReturn(dtoFactory.category());
        String json = toJson(request);
        mockMvc.perform(MockMvcRequestBuilders
                .put(CATEGORY_MAPPING + "/" + 1L)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(json))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }

    @Test
    public void delete() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .delete(CATEGORY_MAPPING + "/" + 1L)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }

}
