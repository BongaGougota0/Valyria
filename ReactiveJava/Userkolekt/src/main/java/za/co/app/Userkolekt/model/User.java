package za.co.app.Userkolekt.model;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.UUID;

@Table(name="users")
public class User {

    public User() {
    }

    public User(String userName, UUID userId, String password, String email) {
        this.userName = userName;
        this.userId = userId;
        this.password = password;
        this.email = email;
    }

    @Id
    @Column(name="user_id")
    private UUID userId;

    @Column(name="user_name")
    @NotBlank(message = "Create a unique username, this cannot be empty")
    @Size(min = 2,max = 40,message = "cannot be shorter than 2 and longer than 40 characters")
    private String userName;

    @Column(name="email")
    @NotBlank
    @Size(min = 2,max = 40,message = "Email cannot be shorter than 2 and longer than 40 characters")
    @Email(message = "Enter valid email address")
    private String email;

    @Column(name="password")
    @NotBlank(message = "Password cannot be empty")
    @Size(min = 2,max = 40,message = "Password cannot be shorter than 2 and longer than 40 characters")
    private String password;

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
