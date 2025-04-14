//edited by Lewis Huang

package nus.iss.team1.grabfreshfood.controller;

import nus.iss.team1.grabfreshfood.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import nus.iss.team1.grabfreshfood.model.Category;

import java.util.List;

@Controller
public class MainController {
    @Autowired
    private CategoryService categoryService;
    // Main landing page
    @GetMapping("/")
    public String landingPage() {
        return "landing-page";
    }

}
