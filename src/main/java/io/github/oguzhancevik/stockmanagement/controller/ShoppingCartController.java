package io.github.oguzhancevik.stockmanagement.controller;

import io.github.oguzhancevik.stockmanagement.model.request.ShoppingRequest;
import io.github.oguzhancevik.stockmanagement.service.ShoppingCartCommandService;
import io.github.oguzhancevik.stockmanagement.util.Constants;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.net.URI;

@RestController
@RequestMapping(Constants.API.SHOPPING_CART_MAPPING)
@Validated
public class ShoppingCartController {

    private final ShoppingCartCommandService commandService;

    public ShoppingCartController(ShoppingCartCommandService commandService) {
        this.commandService = commandService;
    }

    @PostMapping
    public ResponseEntity<URI> shopping(@Valid @NotNull @RequestBody ShoppingRequest request) {
        commandService.shopping(request);
        return ResponseEntity.ok().build();
    }

}
