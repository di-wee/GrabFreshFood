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

    @ModelAttribute
    public void addCategoriesToModel(Model model) {
        List<Category> categories = categoryService.getAllCategoriesWithSubcategories();
        model.addAttribute("categories", categories);
    }
}
