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

@Controller
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepo;

    @GetMapping("/login")
    public String showLoginForm() {
        return "login-page";
    }

    @PostMapping("/login")
    public String doLogin(@RequestParam String username,
                          @RequestParam String password,
                          HttpSession session,
                          Model model) {
        Customer cust = customerRepo.findByUsername(username);

        if (cust != null && cust.getPassword().equals(password)) {
            session.setAttribute("customer", cust);
            return "landing-page";
        }
        model.addAttribute("error", "Invalid username or password");
        model.addAttribute("username", username);
        return "login-page";
    }

    @GetMapping("/logout")
    public String doLogout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
