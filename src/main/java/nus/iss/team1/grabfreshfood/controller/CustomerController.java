//Done by Lewis Huang

package nus.iss.team1.grabfreshfood.controller;

import nus.iss.team1.grabfreshfood.model.Customer;
import nus.iss.team1.grabfreshfood.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

import java.util.Optional;

@Controller
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepo;

    /**
     * Display the login form.
     */
    @GetMapping("/login")
    public String showLoginForm() {
        return "login-page";
    }

    /**
     * Process login credentials against the database.
     * On success, store the Customer in session and redirect to /products.
     * On failure, return to login-page with an error message and the entered username.
     */
    @PostMapping("/login")
    public String doLogin(@RequestParam String username,
                          @RequestParam String password,
                          HttpSession session,
                          Model model) {
        Customer cust = customerRepo.findByUsername(username);
        // Plain-text comparison for demo; use hashing in production
        if (cust != null && cust.getPassword().equals(password)) {
            session.setAttribute("customer", cust);
            //Storing customerId in session to be extracted later for Shopping Cart
            session.setAttribute("customerId", cust.getId());
            return "landing-page";
        }
        model.addAttribute("error", "Invalid username or password");
        model.addAttribute("username", username);
        return "login-page";
    }

    /**
     * Log the user out by invalidating the session.
     */
    @GetMapping("/logout")
    public String doLogout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

    /**
     * Show the profile (account) page for the logged-in customer.
     * Always re-loaded from the database so you see up-to-date values.
     */
    @GetMapping("/account")
    public String viewAccount(HttpSession session, Model model) {
        Customer sessionCust = (Customer) session.getAttribute("customer");
        if (sessionCust == null) {
            return "redirect:/login";
        }
        Optional<Customer> opt = customerRepo.findById(sessionCust.getId());
        if (opt.isEmpty()) {
            // Customer no longer exists → force logout
            session.invalidate();
            return "redirect:/login";
        }
        model.addAttribute("customer", opt.get());
        return "account";
    }
}
