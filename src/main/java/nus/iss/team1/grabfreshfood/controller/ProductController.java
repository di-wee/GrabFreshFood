package nus.iss.team1.grabfreshfood.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import nus.iss.team1.grabfreshfood.model.Product;
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
	public String getProductInfo(@PathVariable("id")int id, Model model) {
		Optional<Product> product = productService.findProductById(id);
		if(product.isPresent()) {
		model.addAttribute("product", product.get());
		} else {
			model.addAttribute("errorMessage","Product not found");
		}

		return "product-details";
	}
	
/*	//have not login yet
	//redirect to login page
	@Autowired
	private CartService cartService;
	
	//check if the user is login before allowing usage of add to cart function
	@PostMapping("/addToCart")
	public String addToCart(HttpSession session,@RequestParam("product_id") int productId) {
		Customer customer = (Customer) session.getAttribute("customer");
		if (customer == null) {
			return "forward:/loginPage";	
		}
			
		cartService.addProductToCart(customer, productId);
		return "redirect:/shoppingCart";
	}
	
	
	//is a login customer
	//if press add to cart goes to shopping cart page 
	//1. find if there is same item in cart
	//2. Same item in cart, find the quantity, add + 1 in rest API
	//3. New item in cart, display the quantity +1
*/
}


