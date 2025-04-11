package nus.iss.team1.grabfreshfood.service;

import nus.iss.team1.grabfreshfood.model.Category;
import nus.iss.team1.grabfreshfood.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategoriesWithSubcategories() {
        return categoryRepository.findAll(); // JPA auto fetches subcategories if LAZY is changed to EAGER
    }
}
