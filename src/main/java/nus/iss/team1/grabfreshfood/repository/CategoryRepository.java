package nus.iss.team1.grabfreshfood.repository;

import nus.iss.team1.grabfreshfood.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    @Query("SELECT DISTINCT c FROM Category c LEFT JOIN FETCH c.subCategories s ORDER BY c.name")
    List<Category> findDistinctCategoriesWithSubcategories();

    List<Category> findById(int categoryId);

}
