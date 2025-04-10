package nus.iss.team1.grabfreshfood.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="product_id", nullable = false)
    private int id;
    @Column(name = "product_name", nullable = false)
    private String name;
    @Column(name="imformation")
    private String description;
    private float price;
    @Column(name = "stock_quantity")
    private int quantity;
    private String unit;
    private String sku;
    private String is_active;

    @ManyToOne
    private SubCategory subCategory;
    @ManyToOne
    private Category category;

    @OneToMany(mappedBy = "product")
    @JsonIgnore
    private List<OrderItems> myOrderItem;

    public Product() {

    }

