package it.uniroma3.siw.fornialegna.controller;

import it.uniroma3.siw.fornialegna.model.Pizza;
import it.uniroma3.siw.fornialegna.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;



@Controller
public class PizzaController {

    @Autowired
    private PizzaService pizzaService;

    @GetMapping("/menu/pizze")
    public List<Pizza> getAllPizze(@RequestParam(required = false, defaultValue = "false") boolean sortedByName,
                                    @RequestParam(required = false, defaultValue = "false") boolean sortedByPrice) {
       
        if (sortedByName) {
            return pizzaService.findAllSortedByNome();
        } else if (sortedByPrice) {
            return pizzaService.findAllSortedByPrezzo();
        } else {
            return pizzaService.findAll();
        }
      
    }

    @GetMapping("/menu/pizze/{id}")
    public Pizza getPizzaById(@PathVariable Long id) {
        return pizzaService.findById(id);
    }

}
