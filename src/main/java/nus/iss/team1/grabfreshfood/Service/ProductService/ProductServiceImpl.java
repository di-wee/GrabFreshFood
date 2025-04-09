package nus.iss.team1.grabfreshfood.Service.ProductService;
import java.util.List;
import java.util.Optional;

import nus.iss.team1.grabfreshfood.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import nus.iss.team1.grabfreshfood.model.Product;

public class ProductServiceImpl {
    @Autowired
    private ProductRepository productRepository;
    List<Product> findProductByQuery(){
        return List<Product>=productRepository.findProductByQuery(query);

    };
}
