package com.tw.bootcamp.bookshop.user;

import com.tw.bootcamp.bookshop.error.ErrorResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.expression.spel.ast.NullLiteral;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    public static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Email is mandatory")
    @Email(regexp = "[A-Za-z0-9.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,3}",message = "Invalid email id !!!") // email validation
    private String email;


    @NotBlank(message = "Password is mandatory")
    @Size(min=8,message = "Password length should be at-least 8 character length and should not contain whitespaces")  // validate password
    private String password;


    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public static User create(CreateUserCommand userCommand) {
        String password = "";

        if (!userCommand.getPassword().trim().isEmpty() && userCommand.getPassword().length()>=8 && !userCommand.getPassword().contains(" ")) {
            password = PASSWORD_ENCODER.encode(userCommand.getPassword());
        }

        return new User(userCommand.getEmail(), password);
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

}
