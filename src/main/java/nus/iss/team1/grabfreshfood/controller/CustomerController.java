//Done by Lewis Huang
package nus.iss.team1.grabfreshfood.controller;

import nus.iss.team1.grabfreshfood.model.Cart;
import nus.iss.team1.grabfreshfood.model.Customer;
import nus.iss.team1.grabfreshfood.repository.CartRepository;
import nus.iss.team1.grabfreshfood.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Controller
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepo;

    // Added to create cart when a customer registers
    @Autowired
    private CartRepository cartRepo;

    /**
     * Display the login form.
     */
    @GetMapping("/login")
    public String showLoginForm() {
        return "login-page";
    }

    /**
     * Process login credentials against the database.
     * On success, store the Customer in session and redirect to the account page.
     * Additionally, if the account is deactivated (isActive false), automatically reactivate it.
     * On failure, return to login-page with an error message.
     */
    @PostMapping("/login")
    public String doLogin(@RequestParam String username,
                          @RequestParam String password,
                          HttpSession session,
                          Model model) {
        Customer cust = customerRepo.findByUsername(username);
        // Plain-text comparison for demo; in production, implement password hashing
        if (cust != null && cust.getPassword().equals(password)) {
            // Reactivate the account if deactivated
            if (cust.getIsActive() == null || !cust.getIsActive()) {
                cust.setIsActive(true);
                customerRepo.save(cust);
            }
            session.setAttribute("customer", cust);
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
     * Display the registration form for new customers.
     */
    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        // Add an empty Customer object so that the registration form can be bound to it.
        model.addAttribute("customer", new Customer());
        return "register";
    }

    /**
     * Process the registration of a new customer.
     * This method sets default values (for registration date and active status),
     * saves the new customer record in the SQL database, and logs the customer in.
     * It also creates a new empty Cart for the customer upon registration.
     * Now includes validation.
     */
    @PostMapping("/register")
    public String processRegister(@Valid @ModelAttribute Customer customer,
                                  BindingResult result,
                                  HttpSession session,
                                  Model model) {
        // Check for validation errors
        if (result.hasErrors()) {
            // Return back to registration form with error messages
            return "register";
        }

        customer.setRegistrationDate(LocalDateTime.now());
        customer.setIsActive(true);
        // Save the customer to the database
        Customer savedCustomer = customerRepo.save(customer);

        // Create a new Cart for the customer after successful registration
        Cart cart = new Cart();
        cart.setCustomerId(savedCustomer.getId());
        cart.setCreationDate(LocalDate.now());
        cartRepo.save(cart);

        // Log in the newly registered customer by storing them in session
        session.setAttribute("customer", savedCustomer);
        session.setAttribute("customerId", savedCustomer.getId());
        return "redirect:/account";
    }

    /**
     * Display the profile (account) page for the logged-in customer.
     * Always loaded fresh from the database to show up-to-date values.
     */
    @GetMapping("/account")
    public String viewAccount(HttpSession session, Model model) {
        Customer sessionCust = (Customer) session.getAttribute("customer");
        if (sessionCust == null) {
            return "redirect:/login";
        }
        Optional<Customer> opt = customerRepo.findById(sessionCust.getId());
        if (opt.isEmpty()) {
            // If customer no longer exists, force logout.
            session.invalidate();
            return "redirect:/login";
        }
        model.addAttribute("customer", opt.get());
        return "account";
    }

    /**
     * Display the account update page for the logged-in customer.
     * This page is separate from the account view page.
     */
    @GetMapping("/account/update")
    public String showAccountUpdate(HttpSession session, Model model) {
        Customer sessionCust = (Customer) session.getAttribute("customer");
        if (sessionCust == null) {
            return "redirect:/login";
        }
        Optional<Customer> opt = customerRepo.findById(sessionCust.getId());
        if (opt.isEmpty()) {
            session.invalidate();
            return "redirect:/login";
        }
        // Pre-populate the account update form with the current customer details.
        model.addAttribute("customer", opt.get());
        return "account-update";
    }

    /**
     * Process account (profile) update.
     * This endpoint updates the personal details of the logged-in customer.
     * The update includes the username (email), first name, last name, phone number, address, and password.
     */
    @PostMapping("/account/update")
    public String updateAccount(@ModelAttribute Customer customer,
                                HttpSession session,
                                Model model) {
        Customer sessionCust = (Customer) session.getAttribute("customer");
        if (sessionCust == null) {
            return "redirect:/login";
        }
        Optional<Customer> opt = customerRepo.findById(sessionCust.getId());
        if (opt.isPresent()) {
            Customer current = opt.get();
            // Update personal details
            current.setUsername(customer.getUsername());
            current.setFirstName(customer.getFirstName());
            current.setLastName(customer.getLastName());
            current.setPhoneNumber(customer.getPhoneNumber());
            current.setAddress(customer.getAddress());
            current.setPassword(customer.getPassword());
            // Save the updated customer record
            customerRepo.save(current);
            // Update session with the new customer data
            session.setAttribute("customer", current);
            model.addAttribute("message", "Profile updated successfully");
            model.addAttribute("customer", current);
            return "redirect:/account";
        } else {
            session.invalidate();
            return "redirect:/login";
        }
    }

    /**
     * Process account deactivation.
     * When a customer opts to deactivate their account, set isActive to false,
     * then invalidate the session. Upon re-logging in, the login method reactivates the account.
     */
    @PostMapping("/account/deactivate")
    public String deactivateAccount(HttpSession session, Model model) {
        Customer sessionCust = (Customer) session.getAttribute("customer");
        if (sessionCust == null) {
            return "redirect:/login";
        }
        Optional<Customer> opt = customerRepo.findById(sessionCust.getId());
        if (opt.isPresent()) {
            Customer current = opt.get();
            current.setIsActive(false);
            customerRepo.save(current);
            session.invalidate();
            // Optionally pass a message that the account is deactivated, which can be displayed on the login page.
            model.addAttribute("message", "Your account has been deactivated. Log in to reactivate.");
            return "redirect:/login";
        } else {
            session.invalidate();
            return "redirect:/login";
        }
    }
}
