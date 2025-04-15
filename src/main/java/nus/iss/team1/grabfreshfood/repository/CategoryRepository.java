package nus.iss.team1.grabfreshfood.repository;

import nus.iss.team1.grabfreshfood.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Category findByName(String name);
}