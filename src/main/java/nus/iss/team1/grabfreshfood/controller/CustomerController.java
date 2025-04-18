// Done by Lewis Huang
package nus.iss.team1.grabfreshfood.controller;

import nus.iss.team1.grabfreshfood.DTO.CustomerRegisterDTO;
import nus.iss.team1.grabfreshfood.DTO.CustomerUpdateDTO;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;

@Controller
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepo;

    @Autowired
    private CartRepository cartRepo;

    // Display the login page
    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("customer", new Customer()); // bind empty Customer object to form
        return "login-page";
    }

    // Process login form submission
    @PostMapping("/login")
    public String doLogin(@RequestParam String username,
                          @RequestParam String password,
                          HttpSession session,
                          Model model) {
        Customer cust = customerRepo.findByUsername(username);
        // Authenticate user credentials
        if (cust != null && cust.getPassword().equals(password)) {
            // If account is inactive, reactivate it
            if (cust.getIsActive() == null || !cust.getIsActive()) {
                cust.setIsActive(true);
                customerRepo.save(cust);
            }
            // Store user in session and redirect to landing page
            session.setAttribute("customer", cust);
            session.setAttribute("customerId", cust.getId());
            return "landing-page";
        }
        // Invalid login attempt
        model.addAttribute("error", "Invalid username or password");
        model.addAttribute("username", username);
        return "login-page";
    }

    // Handle logout and redirect with a contextual message
    @GetMapping("/logout")
    public String doLogout(HttpSession session, RedirectAttributes redirectAttributes) {
        session.invalidate(); // clear session

        // Determine part of day for a contextual goodbye
        LocalTime now = LocalTime.now();
        String greeting;
        if (now.isBefore(LocalTime.NOON)) {
            greeting = "Good morning";
        } else if (now.isBefore(LocalTime.of(18, 0))) {
            greeting = "Good afternoon";
        } else {
            greeting = "Good evening";
        }

        String message = "You have successfully logged out. Thank you for shopping with GrabFreshFood ☺! Have a " + greeting.toLowerCase() + "!";
        redirectAttributes.addFlashAttribute("logoutMessage", message);

        return "redirect:/login";
    }

    // Display registration form
    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("customer", new CustomerRegisterDTO()); // bind empty DTO
        return "register";
    }

    // Process registration submission
    @PostMapping("/register")
    public String processRegister(@Valid @ModelAttribute("customer") CustomerRegisterDTO customerDTO,
                                  BindingResult result,
                                  HttpSession session,
                                  Model model) {
        // Validate if username is already taken
        if (customerRepo.findByUsername(customerDTO.getUsername()) != null) {
            result.rejectValue("username", "error.username", "Username already exists");
        }

        // If validation fails, return to form
        if (result.hasErrors()) {
            System.out.println("Phone number submitted: " + customerDTO.getPhoneNumber());
            result.getAllErrors().forEach(error -> System.out.println("Register validation error: " + error.getDefaultMessage()));
            return "register";   
        }

        // Create and populate new Customer entity
        Customer customer = new Customer();
        customer.setUsername(customerDTO.getUsername());
        customer.setPassword(customerDTO.getPassword());
        customer.setFirstName(customerDTO.getFirstName());
        customer.setLastName(customerDTO.getLastName());
        customer.setPhoneNumber(customerDTO.getPhoneNumber());
        customer.setAddress(customerDTO.getAddress());
        customer.setRegistrationDate(LocalDateTime.now());
        customer.setIsActive(true);

        // Save new customer to DB
        Customer savedCustomer = customerRepo.save(customer);

        // Initialize an empty cart for new user
        Cart cart = new Cart();
        cart.setCustomerId(savedCustomer.getId());
        cart.setCreationDate(LocalDate.now());
        cartRepo.save(cart);

        // Log in new user and redirect
        session.setAttribute("customer", savedCustomer);
        session.setAttribute("customerId", savedCustomer.getId());
        return "redirect:/account";
    }

    // Show account info page
    @GetMapping("/account")
    public String viewAccount(HttpSession session, Model model) {
        Customer sessionCust = (Customer) session.getAttribute("customer");
        if (sessionCust == null) {
            return "redirect:/login";
        }

        Optional<Customer> opt = customerRepo.findById(sessionCust.getId());
        if (opt.isEmpty()) {
            session.invalidate();
            return "redirect:/login";
        }

        model.addAttribute("customer", opt.get());
        return "account";
    }

    // Display account update form with pre-filled values
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

        Customer c = opt.get();
        CustomerUpdateDTO dto = new CustomerUpdateDTO();
        dto.setUsername(c.getUsername());
        dto.setPassword(c.getPassword());
        dto.setFirstName(c.getFirstName());
        dto.setLastName(c.getLastName());
        dto.setPhoneNumber(c.getPhoneNumber());
        dto.setAddress(c.getAddress());

        model.addAttribute("customer", dto);
        return "account-update";
    }

    // Handle form submission for account updates
    @PostMapping("/account/update")
    public String updateAccount(@Valid @ModelAttribute("customer") CustomerUpdateDTO customerDTO,
                                BindingResult result,
                                HttpSession session,
                                Model model) {
        Customer sessionCust = (Customer) session.getAttribute("customer");
        if (sessionCust == null) {
            return "redirect:/login";
        }

        // If validation fails, return to form
        if (result.hasErrors()) {
            result.getAllErrors().forEach(error -> System.out.println("Update validation error: " + error.getDefaultMessage()));
            return "account-update";
        }

        // Update customer data
        Optional<Customer> opt = customerRepo.findById(sessionCust.getId());
        if (opt.isPresent()) {
            Customer current = opt.get();
            current.setUsername(customerDTO.getUsername());
            current.setPassword(customerDTO.getPassword());
            current.setFirstName(customerDTO.getFirstName());
            current.setLastName(customerDTO.getLastName());
            current.setPhoneNumber(customerDTO.getPhoneNumber());
            current.setAddress(customerDTO.getAddress());

            customerRepo.save(current);
            session.setAttribute("customer", current); // update session data
            model.addAttribute("message", "Profile updated successfully");
            return "redirect:/account";
        } else {
            session.invalidate();
            return "redirect:/login";
        }
    }

    // Deactivate account and log user out
    @PostMapping("/account/deactivate")
    public String deactivateAccount(HttpSession session, Model model) {
        Customer sessionCust = (Customer) session.getAttribute("customer");
        if (sessionCust == null) {
            return "redirect:/login";
        }

        Optional<Customer> opt = customerRepo.findById(sessionCust.getId());
        if (opt.isPresent()) {
            Customer current = opt.get();
            current.setIsActive(false); // deactivate
            customerRepo.save(current);
            session.invalidate(); // logout
            model.addAttribute("message", "Your account has been deactivated. Log in to reactivate.");
            return "redirect:/login";
        } else {
            session.invalidate();
            return "redirect:/login";
        }
    }
}
