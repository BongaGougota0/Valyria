package za.co.products.store.service;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import za.co.products.store.model.Product;
import za.co.products.store.repo.ProductRepository;

@Service
public class ProductService {

    private final ProductRepository  productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Mono<Product> getProduct(int id){
        return this.productRepository.findById(id);
    }

    public Mono<Product> updateProduct(int id, Mono<Product> productMono) {
        return this.productRepository.findById(id)
                .flatMap(p -> productMono.doOnNext(pr -> pr.setId(id)))
                .flatMap(this.productRepository::save);
    }
}
