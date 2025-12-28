package it.uniroma3.siw.fornialegna.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import it.uniroma3.siw.fornialegna.model.Pizza;
import it.uniroma3.siw.fornialegna.model.Bibita;
import it.uniroma3.siw.fornialegna.model.Fritto;
import it.uniroma3.siw.fornialegna.repository.PizzaRepository;
import it.uniroma3.siw.fornialegna.repository.BibitaRepository;
import it.uniroma3.siw.fornialegna.repository.FrittoRepository;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private PizzaRepository pizzaRepository;
    
    @Autowired
    private BibitaRepository bibitaRepository;
    
    @Autowired
    private FrittoRepository frittoRepository;

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
    public String aggiungiPizza(@ModelAttribute Pizza pizza, 
                                @RequestParam(value = "file", required = false) MultipartFile file) {
        try {
            if (file != null && !file.isEmpty()) {
                pizza.setImmagine(file.getBytes());
            }
            pizzaRepository.save(pizza);
        } catch (IOException e) {
            e.printStackTrace();
            return "redirect:/admin/pizze?error=upload";
        }
        return "redirect:/admin/pizze";
    }

    @GetMapping("/pizze/delete/{id}")
    public String eliminaPizza(@PathVariable Long id) {
        pizzaRepository.deleteById(id);
        return "redirect:/admin/pizze";
    }

    @PostMapping("/pizze/update")
    public String aggiornaPizza(@RequestParam Long id,  
                                @RequestParam String nome, 
                                @RequestParam String descrizione, 
                                @RequestParam Double prezzo,
                                @RequestParam(value = "file", required = false) MultipartFile file) {
        try {
            Pizza pizza = pizzaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pizza non trovata"));
            
            pizza.setNome(nome);
            pizza.setDescrizione(descrizione);
            pizza.setPrezzo(prezzo);
            
            // Aggiorna l'immagine solo se è stata caricata una nuova
            if (file != null && !file.isEmpty()) {
                pizza.setImmagine(file.getBytes());
            }
            
            pizzaRepository.save(pizza);
        } catch (IOException e) {
            e.printStackTrace();
            return "redirect:/admin/pizze?error=upload";
        }
        return "redirect:/admin/pizze";
    }
    
    // GESTIONE BIBITE
    @GetMapping("/bibite")
    public String gestisciBibite(Model model) {
        List<Bibita> bibite = bibitaRepository.findAll();
        model.addAttribute("bibite", bibite);
        model.addAttribute("bibita", new Bibita());
        return "admin/bibite";
    }

    @PostMapping("/bibite/add")
    public String aggiungiBibita(@ModelAttribute Bibita bibita, 
                                 @RequestParam(value = "file", required = false) MultipartFile file) {
        try {
            if (file != null && !file.isEmpty()) {
                bibita.setImmagine(file.getBytes());
            }
            bibitaRepository.save(bibita);
        } catch (IOException e) {
            e.printStackTrace();
            return "redirect:/admin/bibite?error=upload";
        }
        return "redirect:/admin/bibite";
    }

    @GetMapping("/bibite/delete/{id}")
    public String eliminaBibita(@PathVariable Long id) {
        bibitaRepository.deleteById(id);
        return "redirect:/admin/bibite";
    }

    //modifca bibita
    @PostMapping("/bibite/update")
    public String aggiornaBibita(@RequestParam Long id, 
                                 @RequestParam String nome, 
                                 @RequestParam String descrizione, 
                                 @RequestParam Double prezzo,
                                 @RequestParam(value = "file", required = false) MultipartFile file) {
        try {
            Bibita bibita = bibitaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bibita non trovata"));
            
            bibita.setNome(nome);
            bibita.setDescrizione(descrizione);
            bibita.setPrezzo(prezzo);
            
            // Aggiorna l'immagine solo se è stata caricata una nuova
            if (file != null && !file.isEmpty()) {
                bibita.setImmagine(file.getBytes());
            }
            
            bibitaRepository.save(bibita);
        } catch (IOException e) {
            e.printStackTrace();
            return "redirect:/admin/bibite?error=upload";
        }
        return "redirect:/admin/bibite";
    }
    
    // GESTIONE FRITTI
    @GetMapping("/fritti")
    public String gestisciFritti(Model model) {
        List<Fritto> fritti = frittoRepository.findAll();
        model.addAttribute("fritti", fritti);
        model.addAttribute("fritto", new Fritto());
        return "admin/fritti";
    }

    @PostMapping("/fritti/add")
    public String aggiungiFritto(@ModelAttribute Fritto fritto, 
                                 @RequestParam(value = "file", required = false) MultipartFile file) {
        try {
            if (file != null && !file.isEmpty()) {
                fritto.setImmagine(file.getBytes());
            }
            frittoRepository.save(fritto);
        } catch (IOException e) {
            e.printStackTrace();
            return "redirect:/admin/fritti?error=upload";
        }
        return "redirect:/admin/fritti";
    }

    @GetMapping("/fritti/delete/{id}")
    public String eliminaFritto(@PathVariable Long id) {
        frittoRepository.deleteById(id);
        return "redirect:/admin/fritti";
    }
    
    @PostMapping("/fritti/update")
    public String aggiornaFritto(@RequestParam Long id,
                                 @RequestParam String nome,
                                 @RequestParam String descrizione,
                                 @RequestParam Double prezzo,
                                 @RequestParam(value = "file", required = false) MultipartFile file) {
        try {
            Fritto fritto = frittoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Fritto non trovato"));
            
            fritto.setNome(nome);
            fritto.setDescrizione(descrizione);
            fritto.setPrezzo(prezzo);
            
            // Aggiorna l'immagine solo se è stata caricata una nuova
            if (file != null && !file.isEmpty()) {
                fritto.setImmagine(file.getBytes());
            }
            
            frittoRepository.save(fritto);
        } catch (IOException e) {
            e.printStackTrace();
            return "redirect:/admin/fritti?error=upload";
        }
        return "redirect:/admin/fritti";
    }
}

