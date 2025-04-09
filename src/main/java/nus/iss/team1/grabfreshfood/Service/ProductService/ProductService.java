package nus.iss.team1.grabfreshfood.service.ProductService;

import nus.iss.team1.grabfreshfood.model.Product;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductService {
    public List<Product> findProductByQuery(String query);
}
