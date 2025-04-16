package nus.iss.team1.grabfreshfood.DTO;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerUpdateDTO {

    @NotBlank(message = "Username (email) is required")
    @Email(message = "Please enter a valid email")
    @Pattern(regexp = ".*\\.com$", message = "Email must end with .com")
    private String username;

    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password;

    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    // ✅ Required phone number that must be all digits
    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^\\d+$", message = "Phone number must contain digits only")
    private String phoneNumber;

    private String address;
}
