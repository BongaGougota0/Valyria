package za.co.app.Userkolekt.model;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.UUID;

@Table(name = "products")
public class Product {
    @Id
    @Column(name = "product_id")
    private UUID productId;

    @Column(name = "name")
    @NotBlank(message = "Please provide product name.")
    private String name;

    @Column(name = "description")
    @NotBlank(message = "Product description cannot be null.")
    private String description;

    @Column(name = "image_url")
    @NotBlank(message = "Please provide image url.")
    private String imageUrl;

    @Column(name = "price")
    @NotBlank(message = "Product price cannot be null.")
    private double price;

    @Column(name = "view_count")
    private int viewCount;

    @Column(name = "favourite_count")
    private int favouriteCount;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
