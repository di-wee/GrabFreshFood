package nus.iss.team1.grabfreshfood.service;
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
    public List<Product> findProductByQuery(String query) {
        return productRepository.findProductByQuery(query);
    }

    ;

    @Override
    public List<Product> findProductBySubCategory(String subcategoryName) {
        return productRepository.findProductBySubCategory(subcategoryName);
    }

    @Override
    public List<Product> findAllProduct() {
        return productRepository.findAll();
    }

}