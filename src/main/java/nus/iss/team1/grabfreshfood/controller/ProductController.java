package nus.iss.team1.grabfreshfood.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductController {
	
	@GetMapping("/product-details")
	public String productDetails() {
		return "product-details";
	}
}
