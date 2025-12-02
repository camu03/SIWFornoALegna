package it.uniroma3.siw.fornialegna.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import it.uniroma3.siw.fornialegna.model.Pizza;
import it.uniroma3.siw.fornialegna.repository.PizzaRepository;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private PizzaRepository pizzaRepository;

    @GetMapping
    public String adminDashboard() {
        return "admin/dashboard";
    }

    @GetMapping("/pizze")
    public String gestisciPizze(Model model) {
        List<Pizza> pizze = pizzaRepository.findAll();
        model.addAttribute("pizze", pizze);
        model.addAttribute("pizza", new Pizza());
        return "admin/pizze";
    }

    @PostMapping("/pizze/add")
    public String aggiungiPizza(@ModelAttribute Pizza pizza) {
        pizzaRepository.save(pizza);
        return "redirect:/admin/pizze";
    }

    @GetMapping("/pizze/delete/{id}")
    public String eliminaPizza(@PathVariable Long id) {
        pizzaRepository.deleteById(id);
        return "redirect:/admin/pizze";
    }
}
