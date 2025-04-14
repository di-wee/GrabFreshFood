package nus.iss.team1.grabfreshfood.service;

import jakarta.transaction.Transactional;
import nus.iss.team1.grabfreshfood.config.ProductNotFoundException;
import nus.iss.team1.grabfreshfood.model.Product;
import nus.iss.team1.grabfreshfood.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductImpl implements ProductService {

    private final ProductRepository productRepo;

    public ProductImpl(ProductRepository productRepo) {
        this.productRepo = productRepo;
    }


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


    @Override
    public List<Product> findProductBySubCategory(String subcategoryName) {
        return productRepo.findProductBySubCategory(subcategoryName);
    }

    @Override
    public List<Product> findAllProduct() {
        return productRepo.findAll();
    }

}


//done by Pris
//    @Override
//    public Optional<Product> findProductById(int id) {
//        return productRepo.findProductById(id);
//    }
//}

