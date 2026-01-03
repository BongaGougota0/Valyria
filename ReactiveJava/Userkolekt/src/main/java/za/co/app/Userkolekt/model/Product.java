package za.co.app.Userkolekt.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.UUID;

@Table("products")
public class Product {
    @Id
    @Column("product_id")
    private UUID productId;

    @Column("name")
    @NotBlank(message = "Please provide product name.")
    private String name;

    @Column("description")
    @NotBlank(message = "Product description cannot be null.")
    private String description;

    @Column("image_url")
    @NotBlank(message = "Please provide image url.")
    private String imageUrl;

    @Column("price")
    @NotBlank(message = "Product price cannot be null.")
    private double price;

    @Column("view_count")
    private int viewCount;

    @Column("favourite_count")
    private int favouriteCount;

    @Column("created_at")
    private LocalDateTime createdAt;
}
