package nus.iss.team1.grabfreshfood.controller;

import nus.iss.team1.grabfreshfood.model.Product;
import nus.iss.team1.grabfreshfood.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/search")
public class SearchController {

    @Autowired
    private ProductService productService;

    @GetMapping("/result")
    public List<Product> queryProducts(@RequestParam("query") String query, Model model) {
        model.addAttribute("query", query);
        return productService.findProductByQuery(query);

    }
}

