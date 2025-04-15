package nus.iss.team1.grabfreshfood.controller;
import nus.iss.team1.grabfreshfood.model.Product;
import nus.iss.team1.grabfreshfood.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CategoryController {
    @Autowired
    private ProductService productService;
    @GetMapping("/category/all")
    public String categoryAll(Model model) {
        //List<Product>products=productService.findAllProduct();
        //String categoryName="All";
        //model.addAttribute("category", categoryName);
        //model.addAttribute("products", products);
        return "category-page";
    }
    @GetMapping("/category/{keyword}")
    public String categorySubcategory(@PathVariable ("keyword")String keyword, Model model) {
        //List<Product> products=productService.findProductBySubCategory(subcategoryName);
        model.addAttribute("keyword", keyword);
        // /model.addAttribute("products", products);
        return "category-page";

    }

}
