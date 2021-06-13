package io.github.oguzhancevik.stockmanagement.controller;

import io.github.oguzhancevik.stockmanagement.model.request.ProductRequest;
import io.github.oguzhancevik.stockmanagement.model.response.ProductDTO;
import io.github.oguzhancevik.stockmanagement.service.ProductCommandService;
import io.github.oguzhancevik.stockmanagement.service.ProductQueryService;
import io.github.oguzhancevik.stockmanagement.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(Constants.API.PRODUCT_MAPPING)
@Validated
public class ProductController {

    private final ProductQueryService queryService;
    private final ProductCommandService commandService;

    @Autowired
    public ProductController(ProductQueryService queryService, ProductCommandService commandService) {
        this.queryService = queryService;
        this.commandService = commandService;
    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> list() {
        List<ProductDTO> products = queryService.findProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductDTO> get(@Valid @NotNull @Positive @PathVariable("productId") Long productId) {
        ProductDTO product = queryService.findById(productId);
        return ResponseEntity.ok(product);
    }

    @GetMapping("/search/category/{categoryId}")
    public ResponseEntity<List<ProductDTO>> searchByCategoryId(@Valid @NotNull @Positive @PathVariable("categoryId") Long categoryId) {
        List<ProductDTO> products = queryService.findProductsByCategoryId(categoryId);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/search/subcategory/{subCategoryId}")
    public ResponseEntity<List<ProductDTO>> searchBySubCategoryId(@Valid @NotNull @Positive @PathVariable("subCategoryId") Long subCategoryId) {
        List<ProductDTO> products = queryService.findProductsBySubCategoryId(subCategoryId);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/search/name/{productName}")
    public ResponseEntity<List<ProductDTO>> searchByProductName(@Valid @NotNull @NotEmpty @NotBlank @PathVariable("productName") String productName) {
        List<ProductDTO> products = queryService.findProductsByName(productName);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/search/price/")
    public ResponseEntity<List<ProductDTO>> searchByPrice(@Valid @NotNull @Positive @RequestParam("min") BigDecimal min,
                                                          @Valid @NotNull @Positive @RequestParam("max") BigDecimal max) {
        List<ProductDTO> products = queryService.findProductsByPrice(min, max);
        return ResponseEntity.ok(products);
    }

    @PostMapping
    public ResponseEntity<URI> create(@Valid @NotNull @RequestBody ProductRequest request) {
        ProductDTO product = commandService.create(request);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{productId}").buildAndExpand(product.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{productId}")
    public ResponseEntity<ProductDTO> update(@Valid @NotNull @Positive @PathVariable("productId") Long productId,
                                             @Valid @NotNull @RequestBody ProductRequest request) {
        ProductDTO product = commandService.update(productId, request);
        return ResponseEntity.ok(product);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity delete(@Valid @NotNull @Positive @PathVariable("productId") Long productId) {
        commandService.deleteById(productId);
        return ResponseEntity.ok().build();
    }

}
