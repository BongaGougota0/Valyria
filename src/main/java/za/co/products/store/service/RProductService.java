package za.co.products.store.service;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import za.co.products.store.model.Product;
import za.co.products.store.util.CacheTemplate;

@Service
public class RProductService {

    private final CacheTemplate<Integer, Product> cacheTemplate;

    public RProductService(CacheTemplate<Integer, Product> cacheTemplate) {
        this.cacheTemplate = cacheTemplate;
    }

    // GET
    public Mono<Product> getProduct(int id) {
        return this.cacheTemplate.get(id);
    }

    // PUT
    public Mono<Product> updateProduct(int id, Mono<Product> productMono) {
        return productMono.flatMap(p -> this.cacheTemplate.update(id,p));
    }

    // DELETE
    public Mono<Void> deleteProduct(int id) {
        return this.cacheTemplate.delete(id);
    }

    // INSERT
}
