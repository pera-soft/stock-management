package io.github.oguzhancevik.stockmanagement.controller;

import io.github.oguzhancevik.stockmanagement.model.request.CategoryRequest;
import io.github.oguzhancevik.stockmanagement.model.response.CategoryDTO;
import io.github.oguzhancevik.stockmanagement.service.CategoryQueryService;
import io.github.oguzhancevik.stockmanagement.util.Constants;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(Constants.API.CATEGORY_MAPPING)
@Validated
public class CategoryController {

    private final CategoryQueryService categoryQueryService;

    public CategoryController(CategoryQueryService categoryQueryService) {
        this.categoryQueryService = categoryQueryService;
    }

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> list() {
        List<CategoryDTO> categories = categoryQueryService.findCategories();
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDTO> get(@Valid @NotNull @Positive @PathVariable("categoryId") Long categoryId) {
        CategoryDTO category = categoryQueryService.findById(categoryId);
        return ResponseEntity.ok(category);
    }

    @PostMapping
    public ResponseEntity<URI> create(@Valid @NotNull @RequestBody CategoryRequest request) {
        CategoryDTO category = categoryQueryService.create(request);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{categoryId}").buildAndExpand(category.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryDTO> update(@Valid @NotNull @Positive @PathVariable("categoryId") Long categoryId,
                                              @Valid @NotNull @RequestBody CategoryRequest request) {
        CategoryDTO category = categoryQueryService.update(categoryId, request);
        return ResponseEntity.ok(category);
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity delete(@Valid @NotNull @Positive @PathVariable("categoryId") Long categoryId) {
        categoryQueryService.deleteById(categoryId);
        return ResponseEntity.ok().build();
    }

}
