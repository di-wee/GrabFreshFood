package nus.iss.team1.grabfreshfood.repository;

import nus.iss.team1.grabfreshfood.model.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubCategoryRepository extends JpaRepository<SubCategory, Integer> {
    SubCategory findSubCategoryByName(String name);
}
