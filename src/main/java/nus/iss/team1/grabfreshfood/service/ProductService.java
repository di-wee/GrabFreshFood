package nus.iss.team1.grabfreshfood.service;

import nus.iss.team1.grabfreshfood.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    public List<Product> findProductByQuery(String query);

    public List<Product> findProductBySubCategory(String subcategoryName);

    public List<Product> findProductByCategory(String categoryName);

    public List<Product> findAllProduct();

    Product findProductById(int id);
//    public Optional<Product> findProductById(int id);


}

