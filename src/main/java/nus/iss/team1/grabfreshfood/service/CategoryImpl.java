package nus.iss.team1.grabfreshfood.service;

import jakarta.transaction.Transactional;
import nus.iss.team1.grabfreshfood.model.Category;
import nus.iss.team1.grabfreshfood.model.Product;
import nus.iss.team1.grabfreshfood.model.ProductCategories;
import nus.iss.team1.grabfreshfood.repository.CategoryRepository;
import nus.iss.team1.grabfreshfood.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CategoryImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Category> getAllCategoriesWithSubcategories() {
        // JPA auto fetches subcategories if LAZY is changed to EAGER
        return categoryRepository.findDistinctCategoriesWithSubcategories();
    }


   
}
