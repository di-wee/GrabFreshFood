package nus.iss.team1.grabfreshfood.service;

import nus.iss.team1.grabfreshfood.model.Product;
import java.util.List;

public interface ProductCategoriesService {
    List<Product>getProductsByCategoryId(int categoryId);
}
