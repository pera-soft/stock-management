package io.github.oguzhancevik.stockmanagement.controller;

import io.github.oguzhancevik.stockmanagement.base.BaseUnitTest;
import io.github.oguzhancevik.stockmanagement.model.request.SubCategoryRequest;
import io.github.oguzhancevik.stockmanagement.service.SubCategoryCommandService;
import io.github.oguzhancevik.stockmanagement.service.SubCategoryQueryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static io.github.oguzhancevik.stockmanagement.util.Constants.API.SUB_CATEGORY_MAPPING;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SubCategoryControllerTest extends BaseUnitTest {

    private MockMvc mockMvc;
    private SubCategoryCommandService commandService;

    @BeforeEach
    public void setUp() {
        SubCategoryQueryService queryService = mock(SubCategoryQueryService.class);
        commandService = mock(SubCategoryCommandService.class);
        var controller = new SubCategoryController(queryService, commandService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void list() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get(SUB_CATEGORY_MAPPING)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }

    @Test
    public void get() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get(SUB_CATEGORY_MAPPING + "/" + 15L)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }

    @Test
    public void create() throws Exception {
        SubCategoryRequest request = dtoFactory.subCategoryRequest();
        when(commandService.create(request)).thenReturn(dtoFactory.subCategory());
        String json = toJson(request);
        mockMvc.perform(MockMvcRequestBuilders
                .post(SUB_CATEGORY_MAPPING)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(json))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn();
    }

    @Test
    public void update() throws Exception {
        SubCategoryRequest request = dtoFactory.subCategoryRequest();
        when(commandService.create(request)).thenReturn(dtoFactory.subCategory());
        String json = toJson(request);
        mockMvc.perform(MockMvcRequestBuilders
                .put(SUB_CATEGORY_MAPPING + "/" + 15L)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(json))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }

    @Test
    public void delete() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .delete(SUB_CATEGORY_MAPPING + "/" + 15L)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }

}
