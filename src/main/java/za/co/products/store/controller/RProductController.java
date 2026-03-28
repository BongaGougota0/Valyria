package za.co.products.store.controller;

import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import za.co.products.store.model.Product;
import za.co.products.store.service.RProductService;

@RestController
@RequestMapping("api/v2/products")
public class RProductController {

    private final RProductService productService;

    public RProductController(RProductService productService) {
        this.productService = productService;
    }

    @GetMapping("{id}")
    public Mono<Product> getProduct(@PathVariable int id) {
        return this.productService.getProduct(id);
    }

    @PutMapping("{id}")
    public Mono<Product> updateProduct(@PathVariable int id, @RequestBody Mono<Product> productMono) {
        return this.productService.updateProduct(id, productMono);
    }

    @DeleteMapping("{id}")
    public Mono<Void> deleteProduct(@PathVariable int id) {
        return this.productService.deleteProduct(id);
    }
}
