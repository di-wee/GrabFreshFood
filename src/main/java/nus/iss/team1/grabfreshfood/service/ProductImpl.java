package nus.iss.team1.grabfreshfood.service;
<<<<<<< HEAD
import java.util.List;
import java.util.Optional;

import nus.iss.team1.grabfreshfood.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import nus.iss.team1.grabfreshfood.model.Product;

@Service
@Transactional(readOnly = true)
public class ProductImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Override
    public List<Product> findProductByQuery(String query){
        return productRepository.findProductByQuery(query);
    };
    @Override
    public List<Product> findProductBySubCategory(String subcategoryName) {
        return  productRepository.findProductBySubCategory(subcategoryName);
    }
    @Override
    public List<Product> findAllProduct() {
        return productRepository.findAll();
    }
    @Override
    public Optional<Product> findProductById(int id) {
        return productRepository.findById(id);
=======

import jakarta.transaction.Transactional;
import nus.iss.team1.grabfreshfood.config.ProductNotFoundException;
import nus.iss.team1.grabfreshfood.model.Product;
import nus.iss.team1.grabfreshfood.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ProductImpl implements ProductService {

    private final ProductRepository productRepo;

    public ProductImpl(ProductRepository productRepo) {
        this.productRepo = productRepo;
    }

    //done by Dionis
    @Override
    public Product findProductById(int id) {
        Product product = productRepo.findProductById(id);
        if (product == null) throw new ProductNotFoundException("Product does not exist with ID: " + id);

        return product;

    }

    @Override
    public List<Product> findProductByQuery(String query) {
        return productRepo.findProductByQuery(query);
    }

    ;

    @Override
    public List<Product> findProductBySubCategory(String subcategoryName) {
        return productRepo.findProductBySubCategory(subcategoryName);
    }

    @Override
    public List<Product> findAllProduct() {
        return productRepo.findAll();
>>>>>>> ac93faf624384365b1f293a97ed9477831257069
    }
}
