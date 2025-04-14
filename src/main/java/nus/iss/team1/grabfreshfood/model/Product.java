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

    private float price;

    @Column(name = "stock_quantity")
    private int quantity;

    private String unit;

    @Column(name = "image_url")
    private String imageURL;

    private String sku;

    @ManyToOne
    private SubCategory subCategory;
    @ManyToOne
    private Category category;

    @OneToMany(mappedBy = "product")
    @JsonIgnore
    private List<OrderItems> myOrderItem;

    public Product() { }

	/*
	 * public int getId() { return 0; }
	 */

}