package nus.iss.team1.grabfreshfood.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private int id;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "information", columnDefinition = "TEXT")
    private String information;

    private double price;

    @Column(name = "stock_quantity")
    private int stockQuantity;

    @Column(name = "image_url")
    private String imageURL;

    private String sku;

    @Column(name = "is_active")
    private Boolean isActive;

    @OneToMany(mappedBy = "product")
    private List<OrderItems> myOrderItem;

    public Product(){}

}
