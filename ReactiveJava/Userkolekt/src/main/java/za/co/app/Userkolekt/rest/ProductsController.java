package za.co.app.Userkolekt.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import za.co.app.Userkolekt.model.PageResponse;
import za.co.app.Userkolekt.model.Product;
import za.co.app.Userkolekt.service.ProductsService;

@RestController
@RequestMapping("/products")
public class ProductsController {
    private final ProductsService productsService;

    public ProductsController(ProductsService productsService) {
        this.productsService = productsService;
    }

    @PostMapping("/add")
    public Mono<ResponseEntity<Product>> addProduct(@RequestBody Mono<Product> product) {
        return productsService.addProduct(product)
                .map( product1 -> ResponseEntity.ok().body(product1));
    }

    @GetMapping("/{productId}")
    public Mono<ResponseEntity<Product>> getAProduct(@PathVariable String productId) {
        return productsService.getProduct(productId)
                .map( product1 -> ResponseEntity.ok().body(product1));
    }

    @GetMapping("/all")
    public Flux<Product> allProducts() {
        return productsService.getAllProducts();
    }

    @GetMapping("")
    public Mono<PageResponse<Product>> paginatedProducts(@RequestParam(defaultValue = "0") int page,
                                                         @RequestParam(value = "size", defaultValue = "10") int size) {
        return productsService.getProducts(page, size);
    }
}
