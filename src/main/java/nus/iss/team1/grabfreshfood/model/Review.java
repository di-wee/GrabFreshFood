package nus.iss.team1.grabfreshfood.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;

@Getter
@Setter
@Entity
@Table(name = "reviews")

public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "product_id", nullable = false)
    private int productId;

    @Column(name = "user_id", nullable = false)
    private int userId;

    @Min(1)
    @Max(5)
    @Column(name = "rating", nullable = false)
    private int rating;

    @Column(name = "comment")
    private String comment;
}
