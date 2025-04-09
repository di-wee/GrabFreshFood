package nus.iss.team1.grabfreshfood.controller;

import nus.iss.team1.grabfreshfood.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import nus.iss.team1.grabfreshfood.service.ProductService.ProductService;

@Controller
@RequestMapping ("/search")
public class SearchController {
    @Autowired
    private ProductService productService;
    @GetMapping("/result")
    public String queryProducts(@RequestParam("query")String query, Model model) {
        model.addAttribute("products", productService.findProductByQuery(query));
        return "search-result";
    }


}
