package nus.iss.team1.grabfreshfood.repository;

import nus.iss.team1.grabfreshfood.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
