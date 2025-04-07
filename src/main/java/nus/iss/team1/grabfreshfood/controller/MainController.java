//edited by Lewis Huang

package nus.iss.team1.grabfreshfood.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    // Main landing page
    @GetMapping("/")
    public String landingPage() {
        return "landing-page";
    }

}
