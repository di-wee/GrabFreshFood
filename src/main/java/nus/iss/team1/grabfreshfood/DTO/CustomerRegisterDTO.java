package nus.iss.team1.grabfreshfood.DTO;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import nus.iss.team1.grabfreshfood.validator.FieldMatch;

@Getter
@Setter
@FieldMatch(first = "password", second = "confirmPassword", message = "Passwords do not match")
public class CustomerRegisterDTO {

    @NotBlank(message = "Username is required")
    @Email(message = "Email should be valid")
    @Pattern(regexp = "^[\\w.-]+@[\\w.-]+\\.com$", message = "Email must be a valid address ending with .com")
    private String username; // Treated as email

    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password;

    @NotBlank(message = "Please confirm your password")
    private String confirmPassword;

    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    @Pattern(regexp = "^\\d{8}$", message = "Phone number must be exactly 8 digits")
    private String phoneNumber;

    private String address;
}
