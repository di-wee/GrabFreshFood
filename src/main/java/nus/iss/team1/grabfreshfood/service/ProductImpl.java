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


    //Done by Shi Ying
    @Override
    public Product findProductById(int id) {
        Product product = productRepo.findProductById(id);
        if (product == null) throw new ProductNotFoundException("Product does not exist with ID: " + id);
        return product;

    }

    //Done by Shi Ying
    @Override
    public List<Product> findProductByQuery(String query) {
        return productRepo.findProductByQuery(query);
    }

    //Done by Shi Ying
    @Override
    public List<Product> findProductBySubCategory(String subcategoryName) {
        return productRepo.findProductsBySubCategory_Name(subcategoryName);
    }

    //Done by Shi Ying
    @Override
    public List<Product> findProductByCategory(String categoryName) {
        return productRepo.findProductsByCategory_Name(categoryName);
    }

    //Done by Shi Ying
    @Override
    public List<Product> findAllProduct() {
        try {
            return productRepo.findAll();
        } catch (Exception e) {
            throw new ProductNotFoundException("Error retrieving products...");
        }
    }

}


//done by Pris
//    @Override
//    public Optional<Product> findProductById(int id) {
//        return productRepo.findProductById(id);
//    }
//}

