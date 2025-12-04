package it.uniroma3.siw.fornialegna.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import it.uniroma3.siw.fornialegna.model.Pizza;
import it.uniroma3.siw.fornialegna.repository.PizzaRepository;
import java.util.List;

@Controller
public class MenuController {
 
    @Autowired
    private PizzaRepository pizzaRepository;

    @GetMapping("/menu")
    public String menu(Model model){
        List<Pizza> pizze = pizzaRepository.findAll();
        model.addAttribute("pizze", pizze);
        
        // Aggiungi informazioni autenticazione
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated() && !auth.getName().equals("anonymousUser")) {
            model.addAttribute("isAuthenticated", true);
            model.addAttribute("username", auth.getName());
            model.addAttribute("isAdmin", auth.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN")));
        } else {
            model.addAttribute("isAuthenticated", false);
        }
        
        return "menu";
    }
}
