package za.co.app.Userkolekt.model;

import org.springframework.data.annotation.CreatedDate;
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

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    @Column("created_at")
    @CreatedDate
    private LocalDateTime createdAt;

}
