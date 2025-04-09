//Done by Lewis Huang

package nus.iss.team1.grabfreshfood.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Integer id;           // changed from Long to Integer

    @Column(name = "email", nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "phone_number")
    private String phoneNumber;

    private String address;

    @Column(name = "registration_date")
    private LocalDateTime registrationDate;

    @Column(name = "is_active")
    private Boolean isActive;

    //mapped order
    @OneToMany(mappedBy = "customer")
    private List<Order> myOrders;

    public Customer() { }

    // Getters and setters...

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

    //myOrders setter and getter

    public List<Order> getMyOrders() {
        return myOrders;
    }

    public void setMyOrders(List<Order> myOrders) {
        this.myOrders = myOrders;
    }
}
