package nus.iss.team1.grabfreshfood.DTO;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

/**
 * DTO for updating customer profile information.
 * Includes validation constraints to ensure form input meets business rules.
 */
@Getter
@Setter
public class CustomerUpdateDTO {

    // Email must not be blank, must be a valid email format, and must end with '.com'
    @NotBlank(message = "Username (email) is required")
    @Email(message = "Please enter a valid email")
    @Pattern(regexp = "^[\\w.-]+@[\\w.-]+\\.com$", message = "Email must end with .com")
    private String username;

    // Password must not be blank and must contain at least 6 characters
    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password;

    // First name is required and cannot be blank
    @NotBlank(message = "First name is required")
    private String firstName;

    // Last name is required and cannot be blank
    @NotBlank(message = "Last name is required")
    private String lastName;

    // Phone number must be exactly 8 digits (Follow Singapore's phone number format)
    @Pattern(regexp = "^\\d{8}$", message = "Phone number must be exactly 8 digits")
    private String phoneNumber;

    // Address is optional and does not include validation constraints
    private String address;
}
