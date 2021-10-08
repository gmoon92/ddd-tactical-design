package kitchenpos.products.tobe.ui.controller;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import kitchenpos.products.tobe.application.ProductService;
import kitchenpos.products.tobe.domain.model.Product;
import kitchenpos.products.tobe.ui.dto.ProductRequests;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/products")
@RestController
public class ProductRestController {
    private final ProductService productService;

    public ProductRestController(final ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<Product> create(@RequestBody final ProductRequests.Create request) {
        final Product response = productService.create(request);
        return ResponseEntity.created(URI.create("/api/products/" + response.getId()))
            .body(response);
    }

    @PutMapping("/{productId}/price")
    public ResponseEntity<Product> changePrice(@PathVariable final UUID productId, @RequestBody final ProductRequests.ChangePrice request) {
        return ResponseEntity.ok(productService.changePrice(productId, request));
    }

    @GetMapping
    public ResponseEntity<List<Product>> findAll() {
        return ResponseEntity.ok(productService.findAll());
    }

    @PostMapping("/by-ids")
    public ResponseEntity<List<Product>> findAllByIdIn(@RequestBody final ProductRequests.FindAll request) {
        return ResponseEntity.ok(productService.findAllByIdIn(request));
    }

}