package nus.iss.team1.grabfreshfood.service;


import nus.iss.team1.grabfreshfood.model.Category;
import nus.iss.team1.grabfreshfood.model.Product;
import nus.iss.team1.grabfreshfood.model.ProductCategories;
import nus.iss.team1.grabfreshfood.repository.CategoryRepository;
import nus.iss.team1.grabfreshfood.repository.ProductCategoriesRepository;
import nus.iss.team1.grabfreshfood.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductCategoriesServiceImpl implements ProductCategoriesService {
    @Autowired
    private ProductCategoriesRepository productCategoriesRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Product> getProductsByCategoryName(String categoryName) {
        Category category = categoryRepository.findByName(categoryName);
        if (category == null) {
            return java.util.Collections.emptyList(); // Or throw an exception
        }
        List<ProductCategories> productCategories = productCategoriesRepository.findByCategory_Id(category.getId()); // Use categoryId
        List<Integer> productIds = productCategories.stream()
                .map(pc -> pc.getProduct().getId())
                .collect(Collectors.toList());
        return productRepository.findAllById(productIds);

    }
}