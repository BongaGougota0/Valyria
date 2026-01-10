package za.co.app.Userkolekt.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import za.co.app.Userkolekt.model.PageResponse;
import za.co.app.Userkolekt.model.Product;

public interface IProductsService {
    Mono<Product> addProduct(Mono<Product> product);
    Mono<Product> getProduct(Mono<String> productId);
    Flux<Product> getAllProducts();
    Mono<PageResponse<Product>> getProducts(int page, int size);
}
