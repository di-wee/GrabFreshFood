package nus.iss.team1.grabfreshfood.service;

import nus.iss.team1.grabfreshfood.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    public List<Product> findProductByQuery(String query);
    public List<Product> findProductBySubCategory(String subcategoryName);
    public List<Product> findAllProduct();
<<<<<<< HEAD
    public Optional<Product> findProductById(int id);
}
=======
    Product findProductById(int id);

}
>>>>>>> ac93faf624384365b1f293a97ed9477831257069
