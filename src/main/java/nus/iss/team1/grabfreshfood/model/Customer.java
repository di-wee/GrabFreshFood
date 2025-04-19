// Done by Lewis Huang

package nus.iss.team1.grabfreshfood.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Represents a Customer in the system.
 * Includes personal and login information, along with account status and orders.
 */
//Done by Lewis
@Entity
@Table(name = "customer")
public class Customer {

    // Primary key: auto-incremented customer ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Integer id; // changed from Long to Integer

    // Username used as email; must be unique and match specific pattern
    @NotBlank(message = "Email is required")
    @Pattern(
            regexp = "^[\\w.-]+@[\\w.-]+\\.com$",
            message = "Email must be a valid address ending with .com"
    )
    @Column(name = "email", nullable = false, unique = true)
    private String username;

    // Login password; must be provided
    @NotBlank(message = "Password is required")
    @Column(nullable = false)
    private String password;

    // First and last name; optional at entity level
    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    // Phone number validation: must be digits only
    @NotBlank(message = "Phone number is required")
    @Pattern(
            regexp = "\\d+",
            message = "Phone number must contain only digits"
    )
    @Column(name = "phone_number")
    private String phoneNumber;

    // Optional address field
    private String address;

    // Auto-captured during customer creation
    @Column(name = "registration_date")
    private LocalDateTime registrationDate;

    // Indicates if the account is currently active
    @Column(name = "is_active")
    private Boolean isActive;

    // One-to-many relationship: a customer can have multiple orders
    @OneToMany(mappedBy = "customer")
    private List<Order> myOrders;

    // Default constructor sets registration date
    public Customer() {
        this.registrationDate = LocalDateTime.now();
    }

    // Getters and setters
    public Integer getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String fn) {
        this.firstName = fn;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String ln) {
        this.lastName = ln;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String pn) {
        this.phoneNumber = pn;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String addr) {
        this.address = addr;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime dt) {
        this.registrationDate = dt;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean active) {
        this.isActive = active;
    }

    public List<Order> getMyOrders() {
        return myOrders;
    }

    public void setMyOrders(List<Order> myOrders) {
        this.myOrders = myOrders;
    }
}
