package za.co.app.Userkolekt.service;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import za.co.app.Userkolekt.model.PageResponse;
import za.co.app.Userkolekt.model.Product;
import za.co.app.Userkolekt.repository.ProductsRepository;
import java.util.UUID;

@Service
public class ProductsService implements IProductsService {

    private final ProductsRepository productsRepository;

    public ProductsService(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    @Override
    public Mono<Product> addProduct(Mono<Product> product) {
        return product.flatMap(productsRepository::save);
    }

    @Override
    public Mono<Product> getProduct(String productId) {
        return Mono.just(productId).flatMap(id -> {
            UUID uuid = UUID.fromString(id);
            return productsRepository.findById(uuid);
        });
    }

    @Override
    public Flux<Product> getAllProducts() {
        return productsRepository.findAll();
    }

    @Override
    public Mono<PageResponse<Product>> getProducts(int page, int size) {
//        PageRequest pageRequest = PageRequest.of(page, size);
        Mono<Long> countMono = productsRepository.count();
        Flux<Product> productFlux = productsRepository.findAll()
                .skip(page * size)
                .take(size);
        return Mono.zip(
                productFlux.collectList(),
                countMono,
                (products, total) -> {
                    int totalPages = (int) Math.ceil((double) total / size);
                    return new PageResponse<>(
                            products,
                            page,
                            size,
                            total,
                            totalPages
                    );
                }
        );
    }
}