package za.co.products.metrics.service;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import za.co.products.metrics.model.Product;

@Service
public class ProductService {

    private final ProductCache productCache;

    public ProductService(ProductCache productCache) {
        this.productCache = productCache;
    }

    public Mono<Product> getProductById(int id) {
        return this.productCache.get(id);
    }

    public Mono<Void> deleteProductById(int id) {
        return this.productCache.delete(id);
    }

    public Mono<Product> updateProductById(int id, Mono<Product> productMono) {
        return productMono.flatMap(p -> this.productCache.update(id, p));
    }
}