package nus.iss.team1.grabfreshfood.config;

import jakarta.servlet.http.HttpSession;
import nus.iss.team1.grabfreshfood.model.Category;
import nus.iss.team1.grabfreshfood.model.Customer;
import nus.iss.team1.grabfreshfood.service.CartService;
import nus.iss.team1.grabfreshfood.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.ui.Model;

import java.util.List;


//to pass model attributes across all fragments across pages
@ControllerAdvice
public class GlobalCategoryAdvice {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CartService cartService;

    //Done by Shiying
    @ModelAttribute("categories")
    public List<Category> addCategoriesToModel(Model model) {
        return categoryService.getAllCategoriesWithSubcategories();

    }

    //Done by Dionis
    @ModelAttribute
    public void cartItemBadge(Model model, HttpSession session) {
        Customer cust = (Customer) session.getAttribute("customer");

        if (cust != null) {
            int cartItemCount = cartService.getCartItemCount(cust.getId());
            model.addAttribute("cartItemCount", cartItemCount);

        } else {
            model.addAttribute("cartItemCount", 0);
        }
    }
}
