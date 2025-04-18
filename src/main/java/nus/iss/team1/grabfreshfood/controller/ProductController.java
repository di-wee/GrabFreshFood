package nus.iss.team1.grabfreshfood.controller;

import java.util.List;
import java.util.Optional;

import nus.iss.team1.grabfreshfood.config.ProductNotFoundException;
import nus.iss.team1.grabfreshfood.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;
import nus.iss.team1.grabfreshfood.model.Customer;
import nus.iss.team1.grabfreshfood.model.Product;
import nus.iss.team1.grabfreshfood.service.CartService;
import nus.iss.team1.grabfreshfood.service.ProductService;
import nus.iss.team1.grabfreshfood.model.Review;

@Controller
public class ProductController {
    private final ProductService productService;
    @Autowired
    private ReviewService reviewService;
    @Autowired
    private CartService cartService;

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

        double averageRating = reviewService.getAverageRating(id);
        model.addAttribute("averageRating", averageRating);

        List<Review> allReviews = reviewService.getReviewsByProductId(id);
        int reviewCount = allReviews.size();

        model.addAttribute("reviews", allReviews);
        model.addAttribute("reviewForm", new Review());
        model.addAttribute("reviewCount", reviewCount);

        Customer customer = (Customer) session.getAttribute("customer");
        if (customer != null) {
            boolean alreadyReviewed = reviewService.hasUserReviewedProduct(id, customer.getId());
            model.addAttribute("alreadyReviewed", alreadyReviewed);
        }

        return "product-details";

    }

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
        if (product == null) {
            throw new ProductNotFoundException("ProductId not found for productId: " + productId);
        }
        cartService.addNumberQuantity(customer.getId(), product.getId(), quantity);
        return "redirect:/cart";
    }

    @PostMapping("/product/{id}/review")
    public String submitReview(@PathVariable("id") int productId,
                               @ModelAttribute("reviewForm") Review review,
                               HttpSession session) {

        Customer customer = (Customer) session.getAttribute("customer");
        if (customer == null) {
            return "redirect:/login";
        }

        review.setId(0);
        review.setUserId(customer.getId());
        review.setProductId(productId);

        String displayName = customer.getFirstName();
        review.setUsername(displayName);

        reviewService.addReview(review);

        return "redirect:/product/" + productId;
    }
}


