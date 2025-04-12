package nus.iss.team1.grabfreshfood.service;

import nus.iss.team1.grabfreshfood.model.Category;
import java.util.List;

public interface CategoryService {
    List<Category> getAllCategoriesWithSubcategories();
}
