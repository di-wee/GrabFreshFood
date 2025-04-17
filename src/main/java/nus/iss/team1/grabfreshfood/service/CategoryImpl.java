package nus.iss.team1.grabfreshfood.service;

import jakarta.transaction.Transactional;
import nus.iss.team1.grabfreshfood.model.Category;
import nus.iss.team1.grabfreshfood.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class CategoryImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;


    //Done by Shi Ying
    @Override
    public List<Category> getAllCategoriesWithSubcategories() {
        // JPA auto fetches subcategories if LAZY is changed to EAGER
        return categoryRepository.findDistinctCategoriesWithSubcategories();
    }


}
