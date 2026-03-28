package za.co.products.metrics.repo;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import za.co.products.metrics.model.Product;

@Repository
public interface ProductRepository extends ReactiveCrudRepository<Product, Integer> {
}