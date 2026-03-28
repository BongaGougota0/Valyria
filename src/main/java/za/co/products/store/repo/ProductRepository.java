package za.co.products.store.repo;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import za.co.products.store.model.Product;

@Repository
public interface ProductRepository extends ReactiveCrudRepository<Product, Integer> {
}
