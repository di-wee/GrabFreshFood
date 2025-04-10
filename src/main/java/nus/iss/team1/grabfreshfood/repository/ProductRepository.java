package nus.iss.team1.grabfreshfood.repository;

import nus.iss.team1.grabfreshfood.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    Product findProductById(int ProductId);
}
