package nus.iss.team1.grabfreshfood.repository;

import nus.iss.team1.grabfreshfood.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    Product findProductById(int id);
}
