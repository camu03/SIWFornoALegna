package it.uniroma3.siw.fornialegna.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import it.uniroma3.siw.fornialegna.model.Pizza;
import it.uniroma3.siw.fornialegna.model.Bibita;
import it.uniroma3.siw.fornialegna.model.Fritto;
import it.uniroma3.siw.fornialegna.repository.PizzaRepository;
import it.uniroma3.siw.fornialegna.repository.BibitaRepository;
import it.uniroma3.siw.fornialegna.repository.FrittoRepository;
import java.util.List;

@Controller
public class MenuController {
 
    private final PizzaRepository pizzaRepository;
    private final BibitaRepository bibitaRepository;
    private final FrittoRepository frittoRepository;

    public MenuController(PizzaRepository pizzaRepository, BibitaRepository bibitaRepository, FrittoRepository frittoRepository) {
        this.pizzaRepository = pizzaRepository;
        this.bibitaRepository = bibitaRepository;
        this.frittoRepository = frittoRepository;
    }

    @GetMapping("/menu")
    public String menu(@RequestParam(required = false) String search, Model model, 
                        @RequestParam(required = false) boolean sortedByName, 
                        @RequestParam(required = false) boolean sortedByPrice) {
        // Prendo tutti i dati dal database
        List<Pizza> pizze = pizzaRepository.findAll();
        List<Bibita> bibite = bibitaRepository.findAll();
        List<Fritto> fritti = frittoRepository.findAll();

        // if(search != null && !search.trim().isEmpty()){
        //     pizze = pizzaRepository.findByNomeStartingWithIgnoreCase(search);
        //     bibite = bibitaRepository.findByNomeStartingWithIgnoreCase(search);
        //     fritti = frittoRepository.findByNomeStartingWithIgnoreCase(search);
        // }
        // else{
        //     pizze = pizzaRepository.findAll();
        //     bibite = bibitaRepository.findAll();
        //     fritti = frittoRepository.findAll();
        // }

        
        //se è richiesto l'ordinamento, ordino le pizze per nome
        // if (sortedByName) {
        //     pizze = pizze.stream()
        //         .sorted((p1, p2) -> p1.getNome().compareToIgnoreCase(p2.getNome()))
        //         .collect(Collectors.toList());
        //     bibite = bibite.stream()
        //         .sorted((p1, p2) -> p1.getNome().compareToIgnoreCase(p2.getNome()))
        //         .collect(Collectors.toList());
        //     fritti = fritti.stream()
        //         .sorted((f1, f2) -> f1.getNome().compareToIgnoreCase(f2.getNome()))
        //         .collect(Collectors.toList());  
        //     model.addAttribute("currentSort", "nome");
        // }

        //se è richiesto l'ordinamento, ordino le pizze per prezzo
        // if (sortedByPrice) {
        //     pizze = pizze.stream()
        //         .sorted((p1, p2) -> p1.getPrezzo().compareTo(p2.getPrezzo()))
        //         .collect(Collectors.toList());
        //     bibite = bibite.stream()
        //         .sorted((p1, p2) -> p1.getPrezzo().compareTo(p2.getPrezzo()))
        //         .collect(Collectors.toList());
        //     fritti = fritti.stream()
        //         .sorted((f1, f2) -> f1.getPrezzo().compareTo(f2.getPrezzo()))
        //         .collect(Collectors.toList());
        //     model.addAttribute("currentSort", "prezzo");
        // }

        model.addAttribute("search", search);
        model.addAttribute("pizze", pizze);
        model.addAttribute("bibite", bibite);
        model.addAttribute("fritti", fritti);
        
        // Aggiungi il conteggio delle pizze
        model.addAttribute("totalePizze", pizze.size());
        model.addAttribute("totaleBibite", bibite.size());
        model.addAttribute("totaleFritti", fritti.size());
        
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
