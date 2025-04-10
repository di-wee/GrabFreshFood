package nus.iss.team1.grabfreshfood.service;

import nus.iss.team1.grabfreshfood.model.Product;

import java.util.List;

public interface ProductService {
    public List<Product> findProductByQuery(String query);
    public List<Product> findProductBySubCategory(String subcategoryName);
    public List<Product> findAllProduct();
}
