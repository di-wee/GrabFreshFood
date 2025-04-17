package nus.iss.team1.grabfreshfood.controller;

import java.util.Optional;

import nus.iss.team1.grabfreshfood.config.ProductNotFoundException;
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
	private final ProductService productService;

	@Autowired
	public ProductController(ProductService productService) {
		this.productService = productService;
	}

    //display product details
    @GetMapping("/product/{id}")
    public String getProductInfo(@PathVariable("id") int id, Model model, HttpSession session) {
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
	public String addToCart(HttpSession session,
							@RequestParam("productId") int productId,
							@RequestParam("quantity") int quantity,
							Model model) {
		Customer customer = (Customer) session.getAttribute("customer");
		if (customer == null) {
			return "redirect:/login";
		}
		Product product = productService.findProductById(productId);
		if(product == null){
			throw new ProductNotFoundException("ProductId not found for productId: " + productId);
		}
		cartService.addNumberQuantity(customer.getId(), product.getId(), quantity);
		return "redirect:/cart";
	}

}


