package io.github.oguzhancevik.stockmanagement.controller;

import io.github.oguzhancevik.stockmanagement.model.request.SubCategoryRequest;
import io.github.oguzhancevik.stockmanagement.model.response.SubCategoryDTO;
import io.github.oguzhancevik.stockmanagement.service.SubCategoryCommandService;
import io.github.oguzhancevik.stockmanagement.service.SubCategoryQueryService;
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
@RequestMapping(Constants.API.SUB_CATEGORY_MAPPING)
@Validated
public class SubCategoryController {

    private final SubCategoryQueryService queryService;
    private final SubCategoryCommandService commandService;

    public SubCategoryController(SubCategoryQueryService queryService, SubCategoryCommandService commandService) {
        this.queryService = queryService;
        this.commandService = commandService;
    }

    @GetMapping
    public ResponseEntity<List<SubCategoryDTO>> list() {
        List<SubCategoryDTO> subCategories = queryService.findSubCategories();
        return ResponseEntity.ok(subCategories);
    }

    @GetMapping("/{subCategoryId}")
    public ResponseEntity<SubCategoryDTO> get(@Valid @NotNull @Positive @PathVariable("subCategoryId") Long subCategoryId) {
        SubCategoryDTO subCategory = queryService.findById(subCategoryId);
        return ResponseEntity.ok(subCategory);
    }

    @PostMapping
    public ResponseEntity<URI> create(@Valid @NotNull @RequestBody SubCategoryRequest request) {
        SubCategoryDTO subCategory = commandService.create(request);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{subCategoryId}").buildAndExpand(subCategory.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{subCategoryId}")
    public ResponseEntity<SubCategoryDTO> update(@Valid @NotNull @Positive @PathVariable("subCategoryId") Long subCategoryId,
                                                 @Valid @NotNull @RequestBody SubCategoryRequest request) {
        SubCategoryDTO subCategory = commandService.update(subCategoryId, request);
        return ResponseEntity.ok(subCategory);
    }

    @DeleteMapping("/{subCategoryId}")
    public ResponseEntity delete(@Valid @NotNull @Positive @PathVariable("subCategoryId") Long subCategoryId) {
        commandService.deleteById(subCategoryId);
        return ResponseEntity.ok().build();
    }

}
