package za.co.products.metrics.controller;

import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import za.co.products.metrics.model.Product;
import za.co.products.metrics.service.ProductService;

@RestController
@RequestMapping("api/v1/products")
public class MetricsProductsController {
    private final ProductService productService;

    public MetricsProductsController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping("{id}")
    public Mono<Product> getProduct(@PathVariable int id) {
        return this.productService.getProductById(id);
    }

    @PutMapping("{id}")
    public Mono<Product> updateProduct(@PathVariable int id, @RequestBody Mono<Product> product) {
        return this.productService.updateProductById(id, product);
    }

    @DeleteMapping("{id}")
    public Mono<Void> deleteProduct(@PathVariable int id) {
        return this.productService.deleteProductById(id);
    }
}