package nus.iss.team1.grabfreshfood.service;
import java.util.List;

import nus.iss.team1.grabfreshfood.repository.ProductRepository;
import nus.iss.team1.grabfreshfood.service.ProductService.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import nus.iss.team1.grabfreshfood.model.Product;

@Service
@Transactional(readOnly = true)
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Override
    public List<Product> findProductByQuery(String query){
        return productRepository.findProductByQuery(query);
    };
}
