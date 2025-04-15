package nus.iss.team1.grabfreshfood.config;

import nus.iss.team1.grabfreshfood.model.Category;
import nus.iss.team1.grabfreshfood.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.ui.Model;

import java.util.List;

@ControllerAdvice
public class GlobalCategoryAdvice {

    @Autowired
    private CategoryService categoryService;

    @ModelAttribute("categories")
    public List<Category> addCategoriesToModel(Model model) {
        System.out.println(">>> Populating categories in model");
        return categoryService.getAllCategoriesWithSubcategories();

    }
}
