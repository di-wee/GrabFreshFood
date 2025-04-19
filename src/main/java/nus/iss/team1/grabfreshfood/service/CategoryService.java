package nus.iss.team1.grabfreshfood.service;

import nus.iss.team1.grabfreshfood.model.Category;
import nus.iss.team1.grabfreshfood.model.Product;
import nus.iss.team1.grabfreshfood.model.SubCategory;

import java.util.List;

//Done by Shi Ying
public interface CategoryService {
    List<Category> getAllCategoriesWithSubcategories();


}
