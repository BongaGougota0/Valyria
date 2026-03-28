package za.co.products.store.model;

import lombok.*;
import org.redisson.api.annotation.REntity;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "products")
public class Product {
    @Id
    private Integer id;

    private String description;
    private double price;
}
