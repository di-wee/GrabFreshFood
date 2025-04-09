package nus.iss.team1.grabfreshfood.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, int> {
    Lost<Product> findByNameIgnoreCase(String product_name);
    List<Product> findByDescriptionIgnoreCase(String description);
    List<Product> findByPrice(Double price);

    
}
