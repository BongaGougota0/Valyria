package za.co.app.Userkolekt.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class User {

    public User() {
    }

    public User(String userName, String userId, String password, String email) {
        this.userName = userName;
        this.userId = userId;
        this.password = password;
        this.email = email;
    }

    private String userId;

    @NotBlank(message = "Create a unique username, this cannot be empty")
    @Size(min = 2,max = 40,message = "cannot be shorter than 2 and longer than 40 characters")
    private String userName;

    @NotBlank
    @Size(min = 2,max = 40,message = "Email cannot be shorter than 2 and longer than 40 characters")
    @Email(message = "Enter valid email address")
    private String email;

    @NotBlank(message = "Password cannot be empty")
    @Size(min = 2,max = 40,message = "Password cannot be shorter than 2 and longer than 40 characters")
    private String password;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
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
