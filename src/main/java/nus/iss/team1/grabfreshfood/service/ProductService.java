package nus.iss.team1.grabfreshfood.service;

import nus.iss.team1.grabfreshfood.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    public List<Product> findProductByQuery(String query);

    List<Product> findProductByCategoryOrSubCategory(String name);

    public List<Product> findAllProduct();

    Product findProductById(int id);
//    public Optional<Product> findProductById(int id);


}

