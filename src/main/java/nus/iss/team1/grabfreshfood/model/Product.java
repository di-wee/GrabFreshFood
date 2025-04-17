package nus.iss.team1.grabfreshfood.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id", nullable = false)
    private int id;

    @Column(name = "product_name", nullable = false)
    private String name;

    @Column(name = "information", columnDefinition = "TEXT")
    private String description;

    @Column(name = "price", nullable = false)
    private double price;

    @Column(name = "stock_quantity", nullable = false)
    private int quantity;

    @Column(name = "package_size", nullable = false)
    private double packageSize;

    @Column(name = "unit", nullable = false)
    private String unit;

    @Column(name = "image_url")
    private String imageURL;

    @Column(name = "sku", nullable = false, unique = true)
    private String sku;

    @ManyToOne
    private SubCategory subCategory;

    @ManyToOne
    private Category category;

    @OneToMany(mappedBy = "product")
    @JsonIgnore
    private List<OrderItems> myOrderItem;

    public Product() { }

    // Getter added to use 'quantity' as stock value
    public int getStock() {
        return quantity;
    }
}
