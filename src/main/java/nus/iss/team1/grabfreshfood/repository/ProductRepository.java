package nus.iss.team1.grabfreshfood.repository;

import nus.iss.team1.grabfreshfood.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query("SELECT p from Product p WHERE " +
            "LOWER(p.name) LIKE LOWER(CONCAT('%',:query, '%')) OR " +
            "LOWER(p.subCategory.name) LIKE LOWER(CONCAT('%',:query, '%')) OR " +
            "LOWER(p.category.name) LIKE LOWER(CONCAT('%',:query, '%'))")
    List<Product> findProductByQuery(@Param("query") String query);

    @Query("SELECT p FROM Product p WHERE p.subCategory.name = :subcategory")
    List<Product> findProductBySubCategory(@Param("subcategory") String subcategoryName);

    Product findProductById(int ProductId);

    //Done by Pris
//    @Query("SELECT p FROM Product p WHERE p.id = :id")
//    Optional<Product> findProductById(@Param("id") int id);


}
