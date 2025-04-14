package nus.iss.team1.grabfreshfood.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import nus.iss.team1.grabfreshfood.model.Customer;
import nus.iss.team1.grabfreshfood.model.Product;
import nus.iss.team1.grabfreshfood.service.CartService;
import nus.iss.team1.grabfreshfood.service.ProductService;

@Controller
public class ProductController {
    // "/product-details
    // "/product/{id}
	private final ProductService productService;
	
	@Autowired
	public ProductController(ProductService productService) {
		this.productService = productService;
	}


    //receieve product id, image,name, price, description from the browser that customer click on
    @GetMapping("/product/{id}")
    public String getProductInfo(@PathVariable("id") int id, Model model) {
        Product product = productService.findProductById(id);
        if (product == null) {
            model.addAttribute("errorMessage", "Product not found");
        } else {
            model.addAttribute("product", product);
        }

        return "product-details";
    }
	
	@Autowired
	private CartService cartService;
	
	//check if the user is login before allowing usage of add to cart function
	@PostMapping("/product/addToCart")
	public String addToCart(HttpSession session) {
		Customer customer = (Customer) session.getAttribute("customer");
		Product product =(Product)session.getAttribute("product");
		if (customer == null) {
			return "redirect:/login-page";	
		}
			
		int customerId = customer.getId();
		int productId = product.getId();
		cartService.addProductToCart(customerId, productId);
		return "redirect:/shopping-cart";
	}
	
}


