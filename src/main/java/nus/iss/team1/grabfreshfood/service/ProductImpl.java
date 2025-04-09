package nus.iss.team1.grabfreshfood.service;

import jakarta.transaction.Transactional;
import nus.iss.team1.grabfreshfood.config.ProductNotFoundException;
import nus.iss.team1.grabfreshfood.model.Product;
import nus.iss.team1.grabfreshfood.repository.ProductRepository;
import org.springframework.stereotype.Service;

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
}
