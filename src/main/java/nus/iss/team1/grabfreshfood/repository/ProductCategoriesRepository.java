package nus.iss.team1.grabfreshfood.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import nus.iss.team1.grabfreshfood.model.ProductCategories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductCategoriesRepository extends JpaRepository<ProductCategories, Integer> {
    List<ProductCategories> findByCategory_Id(int categoryId);
}