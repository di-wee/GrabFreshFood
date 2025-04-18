package nus.iss.team1.grabfreshfood.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

//Done by Dionis, LIU SHUTING, Shi Ying
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
    @JoinColumn(name = "subcategory_id")
    @JsonIgnore
    private SubCategory subCategory;

    @ManyToOne
    @JoinColumn(name = "category_id")
    @JsonIgnore
    private Category category;

    @OneToMany(mappedBy = "product")
    @JsonIgnore
    private List<OrderItems> myOrderItem;

    public Product() {
    }

//	/*
//	 * public int getId() { return 0; }
//	 */

}