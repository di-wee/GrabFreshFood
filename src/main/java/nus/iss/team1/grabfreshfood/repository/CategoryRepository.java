package nus.iss.team1.grabfreshfood.repository;

import nus.iss.team1.grabfreshfood.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Category findByName(String name);

    //Done by ShiYing
    @Query("SELECT DISTINCT c FROM Category c WHERE c.subCategories IS NOT EMPTY")
    List<Category> findDistinctCategoriesWithSubcategories();

    //Done by Dionis
    Category findCategoryByName(String categoryName);


}

