package nus.iss.team1.grabfreshfood.repository;
import nus.iss.team1.grabfreshfood.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query("SELECT p from product p join category c. ")
}
