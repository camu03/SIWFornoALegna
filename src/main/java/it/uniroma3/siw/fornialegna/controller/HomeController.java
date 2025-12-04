package it.uniroma3.siw.fornialegna.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated() && !auth.getName().equals("anonymousUser")) {
            model.addAttribute("isAuthenticated", true);
            model.addAttribute("username", auth.getName());
            model.addAttribute("isAdmin", auth.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN")));
        } else {
            model.addAttribute("isAuthenticated", false);
        }
        return "index";
    }
    
    @GetMapping("/home")
    public String homePage(Model model) {
        return home(model);
    }
}
