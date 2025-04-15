package nus.iss.team1.grabfreshfood.controller;

import nus.iss.team1.grabfreshfood.model.Product;
import nus.iss.team1.grabfreshfood.service.ProductCategoriesService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoriesRESTController {

    @Autowired
    private ProductCategoriesService productCategoriesService;

    @GetMapping("/{categoryId}/products")
    public List<Product> getProductsByCategory(@PathVariable int categoryId) {
        return productCategoriesService.getProductsByCategoryId(categoryId);
    }
}
