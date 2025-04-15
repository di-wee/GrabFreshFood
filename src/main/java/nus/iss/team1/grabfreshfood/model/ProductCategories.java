package nus.iss.team1.grabfreshfood.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "product_categories")
public class ProductCategories {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public ProductCategories() {
    }

    public ProductCategories(Product product, Category category) {
        this.product = product;
        this.category = category;
    }


    @Override
    public String toString() {
        return "ProductCategories{" +
                "id=" + product.getId() +
                ", Product=" + product +
                ", category=" + category +
                '}';
    }
}