package nus.iss.team1.grabfreshfood.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import nus.iss.team1.grabfreshfood.model.ProductCategories;
import java.util.List;

public interface ProductCategoriesRepository extends JpaRepository<ProductCategories, Integer> {
    List<ProductCategories> findByCategory_CategoryId(int categoryId);
}

