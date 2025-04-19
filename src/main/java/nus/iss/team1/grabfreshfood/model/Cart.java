package nus.iss.team1.grabfreshfood.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

//Done by Dionis
@Getter
@Setter
@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    private int cartId;

    @Column(name = "customer_id")
    private int customerId;

    @Column(name = "creation_date")
    private LocalDate creationDate;

    @OneToMany(mappedBy = "cart")
    private List<CartItem> cartItems;


}