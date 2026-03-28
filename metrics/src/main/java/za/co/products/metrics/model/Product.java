package za.co.products.metrics.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Setter
@Getter
@Table
@ToString
public class Product {
    @Id
    private Integer id;

    private String description;
    private double price;
}