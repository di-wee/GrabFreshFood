package nus.iss.team1.grabfreshfood.repository;

import nus.iss.team1.grabfreshfood.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    List<ProductCategories> findById(int categoryId);
    @Query("SELECT DISTINCT c FROM Category c LEFT JOIN FETCH c.subCategories s ORDER BY c.name")
    List<Category> findDistinctCategoriesWithSubcategories();
   List<Category> findById(int categoryId);

    Category findByName(String name);
}

