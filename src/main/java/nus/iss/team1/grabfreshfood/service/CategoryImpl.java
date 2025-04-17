package nus.iss.team1.grabfreshfood.service;

import jakarta.transaction.Transactional;
import nus.iss.team1.grabfreshfood.config.CategoryNotFoundException;
import nus.iss.team1.grabfreshfood.model.Category;
import nus.iss.team1.grabfreshfood.model.SubCategory;
import nus.iss.team1.grabfreshfood.repository.CategoryRepository;
import nus.iss.team1.grabfreshfood.repository.ProductRepository;
import nus.iss.team1.grabfreshfood.repository.SubCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class CategoryImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private SubCategoryRepository subCategoryRepository;

    @Override
    public List<Category> getAllCategoriesWithSubcategories() {
        // JPA auto fetches subcategories if LAZY is changed to EAGER
        return categoryRepository.findDistinctCategoriesWithSubcategories();
    }

    @Override
    public Category getCategoryByName(String categoryName) {
        Category cat = categoryRepository.findCategoryByName(categoryName);
        if (cat == null) {
            throw new CategoryNotFoundException("Category( " + categoryName + " ) not found.");
        }
        return cat;
    }

    @Override
    public SubCategory getSubcategoryByName(String subcatName) {
        SubCategory subCat = subCategoryRepository.findSubCategoryByName(subcatName);
        if (subCat == null) {
            throw new CategoryNotFoundException("SubCategory( " + subcatName + " ) not found.");
        }
        return subCat;
    }

}
