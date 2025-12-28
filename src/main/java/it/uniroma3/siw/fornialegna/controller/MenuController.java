package it.uniroma3.siw.fornialegna.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.stream.Collectors;

@Controller
public class MenuController {
 
    @Autowired
    private PizzaRepository pizzaRepository;
    
    @Autowired
    private BibitaRepository bibitaRepository;
    
    @Autowired
    private FrittoRepository frittoRepository;

    @GetMapping("/menu")
    public String menu(@RequestParam(required = false) String search, Model model, 
                        @RequestParam(required = false) boolean sortedByName, 
                        @RequestParam(required = false) boolean sortedByPrice) {
        // Prendo tutti i dati dal database
        List<Pizza> pizze = pizzaRepository.findAll();
        List<Bibita> bibite = bibitaRepository.findAll();
        List<Fritto> fritti = frittoRepository.findAll();
        
        // Se c'è una ricerca, filtro i risultati
        if (search != null && !search.trim().isEmpty()) {
            String searchLower = search.toLowerCase().trim();
            
            // Filtro le pizze: cerco solo parole che iniziano con il testo cercato
            pizze = pizze.stream()
                .filter(p -> containsWordStartingWith(p.getNome(), searchLower) || 
                            containsWordStartingWith(p.getDescrizione(), searchLower))
                .collect(Collectors.toList());
            
            // Filtro le bibite: cerco solo parole che iniziano con il testo cercato
            bibite = bibite.stream()
                .filter(b -> containsWordStartingWith(b.getNome(), searchLower) || 
                            containsWordStartingWith(b.getDescrizione(), searchLower))
                .collect(Collectors.toList());
            
            // Filtro i fritti: cerco solo parole che iniziano con il testo cercato
            fritti = fritti.stream()
                .filter(f -> containsWordStartingWith(f.getNome(), searchLower) || 
                            containsWordStartingWith(f.getDescrizione(), searchLower))
                .collect(Collectors.toList());
            
            // Passo il testo di ricerca alla vista per mostrarlo
            model.addAttribute("search", search);
        }

        //se è richiesto l'ordinamento, ordino le pizze per nome
        if (sortedByName) {
            pizze = pizze.stream()
                .sorted((p1, p2) -> p1.getNome().compareToIgnoreCase(p2.getNome()))
                .collect(Collectors.toList());
            bibite = bibite.stream()
                .sorted((p1, p2) -> p1.getNome().compareToIgnoreCase(p2.getNome()))
                .collect(Collectors.toList());
            fritti = fritti.stream()
                .sorted((f1, f2) -> f1.getNome().compareToIgnoreCase(f2.getNome()))
                .collect(Collectors.toList());  
            model.addAttribute("currentSort", "nome");
        }

        //se è richiesto l'ordinamento, ordino le pizze per prezzo
        if (sortedByPrice) {
            pizze = pizze.stream()
                .sorted((p1, p2) -> p1.getPrezzo().compareTo(p2.getPrezzo()))
                .collect(Collectors.toList());
            bibite = bibite.stream()
                .sorted((p1, p2) -> p1.getPrezzo().compareTo(p2.getPrezzo()))
                .collect(Collectors.toList());
            fritti = fritti.stream()
                .sorted((f1, f2) -> f1.getPrezzo().compareTo(f2.getPrezzo()))
                .collect(Collectors.toList());
            model.addAttribute("currentSort", "prezzo");
        }

        
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
    
    /**
     * Metodo helper che verifica se il testo contiene almeno una parola che inizia con il pattern cercato.
     * Esempio: "PIZZA ROSSA" contiene una parola che inizia con "R" (ROSSA)
     *          "MARGHERITA" NON contiene una parola che inizia con "R"
     */
    private boolean containsWordStartingWith(String text, String search) {
        if (text == null || search == null) {
            return false;
        }
        
        String textLower = text.toLowerCase();
        String searchLower = search.toLowerCase();
        
        // Divido il testo in parole (separo per spazi e segni di punteggiatura)
        String[] words = textLower.split("\\s+|,|;|\\.|!");
        
        // Controllo se almeno una parola inizia con il testo cercato
        for (String word : words) {
            if (word.startsWith(searchLower)) {
                return true;
            }
        }
        
        return false;
    }
}
