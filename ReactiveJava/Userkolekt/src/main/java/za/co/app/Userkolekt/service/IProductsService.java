package za.co.app.Userkolekt.service;

import org.springframework.data.domain.Page;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import za.co.app.Userkolekt.model.Product;

public interface ProductsService {
    Mono<Product> addProduct(Mono<Product> product);
    Flux<Product> getAllProducts();
    Page<Product> getProducts();
}
