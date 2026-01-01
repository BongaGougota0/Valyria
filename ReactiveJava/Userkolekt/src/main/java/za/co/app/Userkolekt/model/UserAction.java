package za.co.app.Userkolekt.model;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

@Table(name="user_actions")
public class UserAction {
    @Id
    private Long id;
    @Column(name = "product_id")
    private String productId;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "action_type")
    private String actionType;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
