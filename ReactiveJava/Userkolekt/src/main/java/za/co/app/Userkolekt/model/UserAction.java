package za.co.app.Userkolekt.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table(name="user_actions")
public class UserAction {
    @Id
    private Long id;
    @Column("product_id")
    private String productId;

    @Column("user_id")
    private String userId;

    @Column("action_type")
    private String actionType;

    @Column("created_at")
    private LocalDateTime createdAt;
}
