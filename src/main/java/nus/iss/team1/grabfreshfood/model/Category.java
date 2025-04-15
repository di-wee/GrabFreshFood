package nus.iss.team1.grabfreshfood.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private int id;
    @Column(name = "category_name", nullable = false)
    private String name;
    @Column(name = "information")
    private String description;


    @OneToMany(mappedBy = "category")
    private List<SubCategory> subCategories;

    public Category() {
    }
}
