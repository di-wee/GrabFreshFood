package nus.iss.team1.grabfreshfood.DTO;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import nus.iss.team1.grabfreshfood.validator.FieldMatch;

/**
 * DTO for customer registration.
 * Includes field-level and class-level validation.
 */
//Done by Lewis
@Getter
@Setter
@FieldMatch(first = "password", second = "confirmPassword", message = "Passwords do not match")
public class CustomerRegisterDTO {

    // Username field, used as email. Must be present, valid format, and end with .com
    @NotBlank(message = "Username is required")
    @Email(message = "Email should be valid")
    @Pattern(regexp = "^[\\w.-]+@[\\w.-]+\\.com$", message = "Email must be a valid address ending with .com")
    private String username;

    // Password is required and must be at least 6 characters
    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password;

    // Confirmation password field, required. Must match the password field (see @FieldMatch)
    @NotBlank(message = "Please confirm your password")
    private String confirmPassword;

    // First name is required
    @NotBlank(message = "First name is required")
    private String firstName;

    // Last name is required
    @NotBlank(message = "Last name is required")
    private String lastName;

    // Phone number must be exactly 8 digits (Follow Singapore's phone number format)
    @Pattern(regexp = "^\\d{8}$", message = "Phone number must be exactly 8 digits")
    private String phoneNumber;

    // Optional address field
    private String address;
}
