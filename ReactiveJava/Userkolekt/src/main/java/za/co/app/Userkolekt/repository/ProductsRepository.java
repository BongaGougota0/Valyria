package za.co.app.Userkolekt.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import za.co.app.Userkolekt.model.Product;

import java.util.UUID;

@Repository
public interface ProductsRepository extends ReactiveCrudRepository<Product, UUID> {
}
